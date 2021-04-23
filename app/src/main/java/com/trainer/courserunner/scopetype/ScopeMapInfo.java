package com.trainer.courserunner.scopetype;

public class ScopeMapInfo {
    private final double startLatitude;
    private final double startLongtitude;
    private final double endLatitude;
    private final double endLongtitude;

    public ScopeMapInfo(double startLatitude,
                        double startLongtitude,
                        double endLatitude,
                        double endLongtitude) {
        this.startLatitude = startLatitude;
        this.startLongtitude = startLongtitude;
        this.endLatitude = endLatitude;
        this.endLongtitude = endLongtitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public double getEndLongtitude() {
        return endLongtitude;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public double getStartLongtitude() {
        return startLongtitude;
    }

    public double getStartX() {
        return startLongtitude;
    }

    public double getStartY() {
        return startLatitude;
    }

    public double getEndX() {
        return endLongtitude;
    }

    public double getEndY() {
        return endLatitude;
    }

}
