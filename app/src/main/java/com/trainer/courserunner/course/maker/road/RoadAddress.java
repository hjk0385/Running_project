package com.trainer.courserunner.course.maker.road;

public class RoadAddress {
    private Double longitude;//x
    private Double latitude;//y

    public RoadAddress() {
        this.longitude = null;
        this.latitude = null;
    }

    public RoadAddress(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getX() {
        return longitude;
    }

    public Double getY() {
        return latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
