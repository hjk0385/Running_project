package com.trainer.courserunner.course.drawer.drawtype;

import android.location.Location;

public class DrawingAddress {
    Location location;

    public DrawingAddress(Location location) {
        this.location = location;
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
}
