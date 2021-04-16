package com.trainer.courserunner.course;

import android.os.AsyncTask;
import android.util.Log;

import com.trainer.courserunner.drawtype.DrawingAddress;
import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.maps.MapFunction;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.rooms.UserCourseInfo;
import com.trainer.courserunner.rooms.UserCourseInfoDao;
import com.trainer.courserunner.rooms.UserLocationPath;

import java.util.List;
import java.util.Map;

public class CourseOverseer{
    //infos
    UserCourseInfo currentCourseInfo;
    UserLocationPath currentLocation;
    //지도 info
    MapDrawer mapDrawer;
    CoursePath[] courseFlags;
    Object[] courseOverlayMarkers;
    Object courseOverlayPath;
    //데이터베이스
    AppDatabase appDatabase;
    public CourseOverseer(MapDrawer mapDrawer){
        this.mapDrawer=mapDrawer;
        this.appDatabase=AppDatabaseLoader.getAppDatabase();
        //데이터 초기화
        this.currentCourseInfo=new UserCourseInfo();
        this.currentLocation=new UserLocationPath();
        this.currentLocation.usercourse_id=0;
        this.currentLocation.userlocation_id=0;
        this.currentLocation.latitude=0;
        this.currentLocation.longitude=0;
    }

    private void drawMap(){
        courseFlags = appDatabase.coursePathDao().loadCoursePath(currentCourseInfo.course_id);
        //그리기
        DrawingPath drawingPath=new DrawingPath(courseFlags);
        courseOverlayPath=mapDrawer.drawCourse(drawingPath);
        courseOverlayMarkers=mapDrawer.drawOverlayMarkers(drawingPath);
        //
    }

    private void registLocation(double latitude, double longtitude){
        //현재위치 등록
        currentLocation.usercourse_id=currentCourseInfo.course_id;
        currentLocation.userlocation_id+=1;
        currentLocation.latitude=latitude;
        currentLocation.longitude=longtitude;
        AppDatabaseLoader.getAppDatabase().userLocationPathDao().insertUserLocationPath(currentLocation);
    }

    //신규시작
    public void startOversight(long courseId,double latitude, double longtitude){
        //사용자 코스 등록
        currentCourseInfo.course_id=courseId;
        currentCourseInfo.usercourse_id=appDatabase.userCourseInfoDao().insertUserCourseInfo(currentCourseInfo);
        drawMap();

    }


    public void updateLocation(double latitude, double longtitude){
        //15m를 기준으로 기록한다.
        double cost=MapFunction.getCost(currentLocation.latitude,currentLocation.longitude,latitude,longtitude);
        if(cost>=15){
            registLocation(latitude,longtitude);
        }
    }
}
