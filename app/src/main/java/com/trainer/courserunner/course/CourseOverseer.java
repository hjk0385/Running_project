package com.trainer.courserunner.course;

import android.location.Location;
import android.util.Log;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.maps.MapFunction;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.rooms.UserCourseInfo;
import com.trainer.courserunner.rooms.UserLocationRecord;
import com.trainer.courserunner.rooms.UserMapFlag;

//데이터를 작성하는 기능 수행
public class CourseOverseer {
    //사용자 감시 단계
    //거리기준 : Double형 변수의 값 1 = 1m, 10 = 10m
    //업데이트 거리
    private final double UPDATE_DISTANCE = 10.0;
    //마커완료 거리
    private final double FINISHMARKER_DISTANCE = 100.0;
    long courseId;
    long usercourseId;
    AppDatabase appDatabase;
    //감시시작
    Location currentLocation;

    public CourseOverseer() {
        this.courseId = -1;
        this.usercourseId = -1;
        appDatabase = AppDatabaseLoader.getAppDatabase();
    }

    //신규시작시
    public long startOversight(long courseId) {
        //코스등록
        this.courseId = courseId;
        this.usercourseId = registUserCourse(courseId);
        //위치정보 초기화
        currentLocation = null;
        return this.usercourseId;
    }

    private long registUserCourse(long courseId) {
        UserCourseInfo userCourseInfo = new UserCourseInfo();
        userCourseInfo.course_id = courseId;
        return appDatabase.userCourseDao().insertUserCourseInfo(userCourseInfo);
    }

    //감시(액티비티에서 호출)
    public boolean updateUserLocation(Location location) {
        if (checkDistance(location)) {
            updateUserLocationInnerProcess(location);
            return true;
        }
        return false;
    }

    public boolean checkDistance(Location location) {
        if (currentLocation == null) {
            return true;
        }
        return MapFunction.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                location.getLatitude(), location.getLongitude()) >= UPDATE_DISTANCE;
    }

    //감시 내부처리 (DB처리)
    private void updateUserLocationInnerProcess(Location location) {
        currentLocation = location;
        registUserLocationRecord(location);
        //마커처리
        MapFlag[] mapFlags = appDatabase.courseDao().getCourseMapflags(courseId);
        for (MapFlag mapFlag : mapFlags) {
            if (MapFunction.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    mapFlag.latitude, mapFlag.longitude) <= FINISHMARKER_DISTANCE) {
                UserMapFlag userMapFlag = new UserMapFlag();
                userMapFlag.usercourse_id = usercourseId;
                userMapFlag.mapflag_id = mapFlag.mapflag_id;
                appDatabase.userCourseDao().insertUserMapFlag(userMapFlag);
            }
        }
    }

    private void registUserLocationRecord(Location location) {
        UserLocationRecord userLocationRecord = new UserLocationRecord();
        userLocationRecord.userlocation_order = appDatabase.userCourseDao().
                getNextUserLocationOrder(usercourseId);
        userLocationRecord.usercourse_id = usercourseId;
        userLocationRecord.latitude = location.getLatitude();
        userLocationRecord.longitude = location.getLongitude();
        //컬러처리
        
        appDatabase.userCourseDao().insertUserLocationRecord(userLocationRecord);
    }
}
