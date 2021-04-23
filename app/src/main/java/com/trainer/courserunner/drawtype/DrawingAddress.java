package com.trainer.courserunner.drawtype;

import com.trainer.courserunner.rooms.UserLocationRecord;

public class DrawingAddress {
    double latitude;
    double longitude;

    public DrawingAddress(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DrawingAddress(UserLocationRecord userLocationRecord){
        this.latitude=userLocationRecord.latitude;
        this.longitude=userLocationRecord.longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
