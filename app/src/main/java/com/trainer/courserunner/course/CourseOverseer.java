package com.trainer.courserunner.course;

import android.location.Location;
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
import com.trainer.courserunner.rooms.UserCoursePath;
import com.trainer.courserunner.rooms.UserLocationPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//데이터를 작성하는 기능 수행
public class CourseOverseer extends CourseDrawer {
    long usercourseId;
    Location currentLocation;

    public CourseOverseer(MapDrawer mapDrawer) {
        super(mapDrawer);
        this.usercourseId=-1;
    }

    //신규시작
    public void startOversight(long courseId){
        //코스 등록
        UserCourseInfo userCourseInfo=new UserCourseInfo();
        userCourseInfo.course_id=courseId;
        this.usercourseId=AppDatabaseLoader.getAppDatabase().userCourseInfoDao().insertUserCourseInfo(userCourseInfo);
        //코스 그리기
        drawCourse(courseId);
        //위치정보 초기화
        currentLocation=null;
    }

    public void updateOversight(Location location){
        if(currentLocation==null){
            currentLocation=location;
            oversight();
        }
        else{
            //이동거리가 10m이상이면 갱신
            if(MapFunction.getDistance(currentLocation.getLatitude(),currentLocation.getLongitude(),
                    location.getLatitude(),location.getLongitude())>=10){
                currentLocation=location;
                oversight();
            }
        }
    }

    private void oversight(){
        //지나갔던 경로의 저장
        AppDatabase appDatabase=AppDatabaseLoader.getAppDatabase();
        UserLocationPath userLocationPath=new UserLocationPath();
        userLocationPath.usercourse_id=usercourseId;
        userLocationPath.userlocation_id=appDatabase.userLocationPathDao().queryMaxUserLocationId(usercourseId)+1;
        userLocationPath.latitude=currentLocation.getLatitude();
        userLocationPath.longitude=currentLocation.getLongitude();
        appDatabase.userLocationPathDao().insertUserLocationPath(userLocationPath);
        drawUserLocationPath(usercourseId);
        //지나간 경로 저장 / 마커제거
        for(int i=0;i<courseFlags.length;i++){
            if(MapFunction.getDistance(currentLocation.getLatitude(),currentLocation.getLongitude(),
                    courseFlags[i].latitude,courseFlags[i].longtitude)<=100){
                UserCoursePath userCoursePath=new UserCoursePath();
                userCoursePath.usercourse_id=usercourseId;
                userCoursePath.coursepath_id=i;
                appDatabase.userCoursePathDao().insertUserCoursePath(userCoursePath);
                clearMarker(i);
            }
        }
    }
}
