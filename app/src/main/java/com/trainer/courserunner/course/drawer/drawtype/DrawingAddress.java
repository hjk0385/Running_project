package com.trainer.courserunner.course.drawer.drawtype;

import android.location.Location;

import com.trainer.courserunner.rooms.UserCourseRecord;

public class DrawingAddress {
    double latitude;
    double longitude;

    public DrawingAddress(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public DrawingAddress(UserCourseRecord userCourseRecord) {
        this.latitude = userCourseRecord.userCourseRecordLatitude;
        this.longitude = userCourseRecord.userCourseRecordLongitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
