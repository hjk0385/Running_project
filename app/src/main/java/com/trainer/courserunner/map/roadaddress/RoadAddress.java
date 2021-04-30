package com.trainer.courserunner.map.roadaddress;

public class RoadAddress {
    private double longitude;//x
    private double latitude;//y

    public RoadAddress() {
        this.longitude = -1;
        this.latitude = -1;
    }

    public RoadAddress(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getX() {
        return longitude;
    }

    public double getY() {
        return latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
