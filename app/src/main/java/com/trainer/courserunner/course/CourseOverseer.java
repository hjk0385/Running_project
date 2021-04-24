package com.trainer.courserunner.course;

import android.graphics.Color;
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

/*
    중간발표 이후에 개선할 내용
    1. AsyncTask를 활용하여 추가적인 쓰레드를 생성하여 처리하여 메인쓰레드가 다른 일을 처리할 수 있도록 만든다.
    2. 작동방식은 무한루프로 진행되지만 실행후에 쓰레드를 멈추고
    사용자의 위치 이벤트가 발생하면 쓰레드를 다시 깨워서 처리하는 형태로 제작한다.
    3. 추가모드들을 지원하도록 추상클래스로 바꾸고 기능을 분리하여 상속받아서 구현한다.
*/

//데이터를 작성하는 기능 수행
public class CourseOverseer {
    //사용자 감시 단계
    //거리기준 : Double형 변수의 값 1 = 1m, 10 = 10m
    //업데이트 거리
    private final double UPDATE_DISTANCE = 1.0;
    //마커완료 거리
    private final double FINISHMARKER_DISTANCE = 150.0;
    long courseId;
    long usercourseId;
    AppDatabase appDatabase;
    //감시시작
    Location currentLocation;
    //현재속성
    int currentLineColor = Color.RED;

    public CourseOverseer() {
        this.courseId = -1;
        this.usercourseId = -1;
        appDatabase = AppDatabaseLoader.getAppDatabase();
    }

    public void setCurrentLineColor(int color) {
        this.currentLineColor = color;
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

    //감시(액티비티에서 호출)
    public boolean updateUserLocation(Location location) {
        if (checkDistance(location)) {
            updateUserLocationInnerProcess(location);
            return true;
        }
        return false;
    }

    private long registUserCourse(long courseId) {
        UserCourseInfo userCourseInfo = new UserCourseInfo();
        userCourseInfo.course_id = courseId;
        Log.v("LOGAA", String.valueOf(courseId));
        return appDatabase.userCourseDao().insertUserCourseInfo(userCourseInfo);
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
        userLocationRecord.color = currentLineColor;
        appDatabase.userCourseDao().insertUserLocationRecord(userLocationRecord);
    }
}
