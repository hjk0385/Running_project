package com.trainer.courserunner.course.overseer;

import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.map.geo.DistanceConverter;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

//기본모드(사용자의 위치만 감시)
public class CourseOverseer extends AsyncTask<Location,Void,Void> implements Observer {
    protected Location currentLocation;
    protected long usercourseId;
    private int currentLineColor;
    public CourseOverseer(long usercourseId){
        this.usercourseId=usercourseId;
        this.currentLocation=null;
        this.currentLineColor=Color.RED;
    }

    private boolean checkUpdateDistance(Location location) {
        final double UPDATE_DISTANCE=100.0;
        return DistanceConverter.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                location.getLatitude(), location.getLongitude()) >= UPDATE_DISTANCE;
    }

    @Override
    public void update(Observable observable, Object o) {
        Location location=(Location)o;
        if(currentLocation==null||checkUpdateDistance(location)){
            currentLocation=location;
            this.execute(location);
        }
    }

    public void setCurrentLineColor(int currentLineColor) {
        this.currentLineColor = currentLineColor;
    }

    @Override
    protected Void doInBackground(Location... locations) {
        registUserLocationRecord(locations[0]);
        return null;
    }

    private void registUserLocationRecord(Location location) {
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();

        UserCourseRecord userCourseRecord=new UserCourseRecord();
        userCourseRecord.userCourseId=usercourseId;
        userCourseRecord.userCourseRecordId=appDatabase.userCourseRecordDao().getNextUserLocationOrder(usercourseId);
        userCourseRecord.userCourseRecordLatitude=location.getLatitude();
        userCourseRecord.userCourseRecordLongitude=location.getLongitude();
        userCourseRecord.userCourseRecordColor = currentLineColor;
        userCourseRecord.userCourseRecordDate=new Date();

        appDatabase.userCourseRecordDao().insertDto(userCourseRecord);
    }
}
