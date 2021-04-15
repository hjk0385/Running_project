package com.trainer.courserunner.drawtype;

public class DrawingAddress {
    double latitude;
    double longitude;
    public DrawingAddress(double latitude, double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
