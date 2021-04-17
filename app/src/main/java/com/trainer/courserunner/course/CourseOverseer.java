package com.trainer.courserunner.course;

import android.location.Location;
import android.util.Log;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.maps.MapFunction;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.UserCourseInfo;
import com.trainer.courserunner.rooms.UserLocationRecord;
import com.trainer.courserunner.rooms.UserMapFlag;

//데이터를 작성하는 기능 수행
public class CourseOverseer extends CourseDrawer {
    long usercourseId;
    Location currentLocation;
    AppDatabase appDatabase;


    //거리기준 : 1 = 1m, 10 = 10m
    //업데이트 거리
    private final double UPDATE_DISTANCE=10.0;
    //마커완료 거리
    private final double FINISHMARKER_DISTANCE=100.0;

    public CourseOverseer(MapDrawer mapDrawer) {
        super(mapDrawer);
        this.usercourseId = -1;
        appDatabase = AppDatabaseLoader.getAppDatabase();
    }

    private long registUserCourse(long courseId) {
        UserCourseInfo userCourseInfo = new UserCourseInfo();
        userCourseInfo.course_id = courseId;
        if(appDatabase==null){
            Log.v("error11","error null");
        }

        return appDatabase.userCourseInfoDao().insertUserCourseInfo(userCourseInfo);
    }


    //신규시작
    public void startOversight(long courseId) {
        //코스등록
        this.usercourseId = registUserCourse(courseId);
        //코스 그리기
        drawCourse(courseId);
        //위치정보 초기화
        currentLocation = null;
    }

    public void updateOversight(Location location) {
        if (currentLocation == null) {
            currentLocation = location;
            oversight();
        } else {
            //이동거리가 10m이상이면 갱신
            if (MapFunction.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    location.getLatitude(), location.getLongitude()) >= UPDATE_DISTANCE) {
                currentLocation = location;
                oversight();
            }
        }
    }

    private void registUserLocationRecord(double latitude, double longitude) {
        UserLocationRecord userLocationRecord = new UserLocationRecord();
        userLocationRecord.userlocation_order = appDatabase.userLocationRecordDao().
                getNextUserLocationOrder(usercourseId);
        userLocationRecord.usercourse_id = usercourseId;
        userLocationRecord.latitude = latitude;
        userLocationRecord.longitude = longitude;
        appDatabase.userLocationRecordDao().insertUserLocationRecord(userLocationRecord);
    }


    private void oversight() {
        //지나가는 경로의 저장
        registUserLocationRecord(currentLocation.getLatitude(), currentLocation.getLongitude());
        drawUserLocationPath(usercourseId);

        for (int i = 0; i < mapFlags.length; i++) {
            if (MapFunction.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    mapFlags[i].latitude, mapFlags[i].longitude) <= FINISHMARKER_DISTANCE) {
                UserMapFlag userMapFlag = new UserMapFlag();

                userMapFlag.usercourse_id = usercourseId;
                userMapFlag.mapflag_id = mapFlags[i].mapflag_id;
                appDatabase.userMapFlagDao().insertUserMapFlag(userMapFlag);
                clearFlag(mapFlags[i].mapflag_id);
            }
        }
    }
}
