package com.trainer.courserunner.course.component.overseer;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.course.component.CourseComponent;
import com.trainer.courserunner.map.geo.DistanceConverter;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.Date;

//결과값 : Boolean 변경되었을시 : true, 변경되지 않은 경우 : false
public class CourseOverseerUserRecord extends CourseComponent {
    private final Long usercourseId;
    Location currentLocation;
    private Integer currentLineColor;

    public CourseOverseerUserRecord(Long userCourseId) {
        this.usercourseId = userCourseId;
        this.currentLineColor = Color.RED;
    }

    public void setCurrentLineColor(Integer currentLineColor) {
        this.currentLineColor = currentLineColor;
    }

    @Override
    protected Object runInWorkThread() {

        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();

        UserCourseRecord userCourseRecord = new UserCourseRecord();
        userCourseRecord.userCourseId = usercourseId;
        userCourseRecord.userCourseRecordId = appDatabase.userCourseRecordDao().getNextUserLocationOrder(usercourseId);
        userCourseRecord.userCourseRecordLatitude = currentLocation.getLatitude();
        userCourseRecord.userCourseRecordLongitude = currentLocation.getLongitude();
        userCourseRecord.userCourseRecordColor = currentLineColor;
        userCourseRecord.userCourseRecordDate = new Date();

        appDatabase.userCourseRecordDao().insertDto(userCourseRecord);
        return null;

    }

    public void refreshLocation(Location location) {
        if (currentLocation == null || checkUpdateDistance(currentLocation, location)) {
            currentLocation = location;
            runComponent();
        }
    }


    private boolean checkUpdateDistance(Location location1, Location location2) {
        final double UPDATE_DISTANCE = 10.0;
        return DistanceConverter.getDistance(location1.getLatitude(), location1.getLongitude(),
                location2.getLatitude(), location2.getLongitude()) >= UPDATE_DISTANCE;
    }
}
