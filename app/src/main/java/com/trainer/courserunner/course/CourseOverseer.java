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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//데이터를 작성하는 기능 수행
public class CourseOverseer extends CourseDrawer{
    //infos
    UserCourseInfo currentCourseInfo;
    UserLocationPath currentLocation;
    //데이터베이스
    AppDatabase appDatabase;
    public CourseOverseer(MapDrawer mapDrawer){
        super(mapDrawer);
        this.appDatabase=AppDatabaseLoader.getAppDatabase();
    }

    //신규시작
    public void startOversight(long courseId){
        long userCourseId=appDatabase.userCourseInfoDao().queryMaxUserCourseId()+1;
        //코스정보 등록
        currentCourseInfo=new UserCourseInfo();
        currentCourseInfo.course_id=courseId;
        currentCourseInfo.usercourse_id=userCourseId;
        appDatabase.userCourseInfoDao().insertUserCourseInfo(currentCourseInfo);
        //코스경로지정
        currentLocation=new UserLocationPath();
        currentLocation.usercourse_id=userCourseId;
        currentLocation.userlocation_id=-1;
        //코스그리기
        drawCourse(courseId);
    }


    private void checkFlags(){
        for(int i=0;i<courseFlags.length;i++){
            if(MapFunction.getCost(currentLocation.latitude,currentLocation.longitude,
                                courseFlags[i].latitude,courseFlags[i].longtitude)<=15){
                this.clearMarker(i);
            }
        }
    }

    public void updateLocation(double latitude, double longtitude){
        //15m를 기준으로 기록한다.
        double cost=MapFunction.getCost(currentLocation.latitude,currentLocation.longitude,latitude,longtitude);
        if(cost>=15){
            //갱신
            currentLocation.userlocation_id+=1;
            currentLocation.latitude=latitude;
            currentLocation.longitude=longtitude;
            AppDatabaseLoader.getAppDatabase().userLocationPathDao().insertUserLocationPath(currentLocation);
            //체크 및 맵에 쓰기
            checkFlags();
        }
    }
}
