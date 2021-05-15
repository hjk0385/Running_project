package com.trainer.courserunner.course.component.overseer;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.Date;

public class CourseOverseerUserRecord extends CourseOverseer {
    private Integer currentLineColor;

    public CourseOverseerUserRecord(Long usercourseId) {
        super(usercourseId);
        this.currentLineColor = Color.RED;
    }

    public void setCurrentLineColor(Integer currentLineColor) {
        this.currentLineColor = currentLineColor;
    }

    @Override
    public void oversight(Location location) {
        registUserLocationRecord(location);
    }

    private void registUserLocationRecord(Location location) {
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();

        UserCourseRecord userCourseRecord = new UserCourseRecord();
        userCourseRecord.userCourseId = usercourseId;
        userCourseRecord.userCourseRecordId = appDatabase.userCourseRecordDao().getNextUserLocationOrder(usercourseId);
        userCourseRecord.userCourseRecordLatitude = location.getLatitude();
        userCourseRecord.userCourseRecordLongitude = location.getLongitude();
        userCourseRecord.userCourseRecordColor = currentLineColor;
        userCourseRecord.userCourseRecordDate = new Date();

        appDatabase.userCourseRecordDao().insertDto(userCourseRecord);
    }
}
