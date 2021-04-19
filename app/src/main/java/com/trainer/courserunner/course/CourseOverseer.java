package com.trainer.courserunner.course;

import android.location.Location;
import android.os.AsyncTask;
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
public class CourseOverseer extends AsyncTask<Location,Void,Boolean> {
    long courseId;
    long usercourseId;
    AppDatabase appDatabase;
    public CourseOverseer() {
        this.courseId=-1;
        this.usercourseId = -1;
        appDatabase = AppDatabaseLoader.getAppDatabase();
    }

    //백그라운드 실행
    CourseDrawer publishUIDrawer;
    public void setPublishUIDrawer(CourseDrawer publishUIDrawer){
        this.publishUIDrawer=publishUIDrawer;
    }

    @Override
    protected Boolean doInBackground(Location... locations) {
        return updateUserLocation(locations[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean){
            publishUIDrawer.mapRefresh();
        }
    }

    //감시시작
    Location currentLocation;
    //신규시작시
    public long startOversight(long courseId) {
        //코스등록
        this.courseId=courseId;
        this.usercourseId = registUserCourse(courseId);
        //위치정보 초기화
        currentLocation = null;
        return this.usercourseId;
    }

    private long registUserCourse(long courseId) {
        UserCourseInfo userCourseInfo = new UserCourseInfo();
        userCourseInfo.course_id = courseId;
        return appDatabase.userCourseInfoDao().insertUserCourseInfo(userCourseInfo);
    }

    //사용자 감시 단계
    //거리기준 : Double형 변수의 값 1 = 1m, 10 = 10m
    //업데이트 거리
    private final double UPDATE_DISTANCE=10.0;
    //마커완료 거리
    private final double FINISHMARKER_DISTANCE=100.0;
    //감시(액티비티에서 호출)
    private boolean updateUserLocation(Location location){
        if(checkDistance(location)){
            updateUserLocationInnerProcess(location);
            return true;
        }
        return false;
    }

    //업데이트 거리확인
    public boolean checkDistance(Location location){
        if(currentLocation==null){
            return true;
        }
        return MapFunction.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                location.getLatitude(), location.getLongitude()) >= UPDATE_DISTANCE;
    }

    //감시 내부처리 (DB처리)
    private void updateUserLocationInnerProcess(Location location){
        currentLocation=location;
        registUserLocationRecord(location);
        //마커처리
        MapFlag[] mapFlags=appDatabase.courseDao().getCourseMapflags(courseId);
        for (MapFlag mapFlag : mapFlags) {
            if (MapFunction.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    mapFlag.latitude, mapFlag.longitude) <= FINISHMARKER_DISTANCE) {
                UserMapFlag userMapFlag = new UserMapFlag();
                userMapFlag.usercourse_id = usercourseId;
                userMapFlag.mapflag_id = mapFlag.mapflag_id;
                appDatabase.userMapFlagDao().insertUserMapFlag(userMapFlag);
            }
        }
    }

    private void registUserLocationRecord(Location location) {
        UserLocationRecord userLocationRecord = new UserLocationRecord();
        userLocationRecord.userlocation_order = appDatabase.userLocationRecordDao().
                getNextUserLocationOrder(usercourseId);
        userLocationRecord.usercourse_id = usercourseId;
        userLocationRecord.latitude = location.getLatitude();
        userLocationRecord.longitude = location.getLongitude();
        appDatabase.userLocationRecordDao().insertUserLocationRecord(userLocationRecord);
    }
}
