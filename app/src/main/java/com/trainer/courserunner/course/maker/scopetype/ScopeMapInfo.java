package com.trainer.courserunner.course.maker.scopetype;

import com.trainer.courserunner.map.geo.DistanceConverter;

public class ScopeMapInfo {
    private final Double startLatitude;
    private final Double startLongtitude;
    private final Double endLatitude;
    private final Double endLongtitude;

    private ScopeMapInfo(Double startLatitude,
                        Double startLongtitude,
                        Double endLatitude,
                        Double endLongtitude) {
        this.startLatitude = startLatitude;
        this.startLongtitude = startLongtitude;
        this.endLatitude = endLatitude;
        this.endLongtitude = endLongtitude;
    }

    public Double getEndLatitude() {
        return endLatitude;
    }

    public Double getEndLongtitude() {
        return endLongtitude;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public Double getStartLongtitude() {
        return startLongtitude;
    }

    public Double getStartX() {
        return startLongtitude;
    }

    public Double getStartY() {
        return startLatitude;
    }

    public Double getEndX() {
        return endLongtitude;
    }

    public Double getEndY() {
        return endLatitude;
    }

    //m단위
    static public ScopeMapInfo makeScopeMapInfoOriginLeftDown(double latitude, double longitude, double distance){
        double startLatitude=latitude;
        double startLongitude=longitude;
        double endLatitude=latitude+ DistanceConverter.convertMeterToLatitude(distance);
        double endLongitude=longitude+DistanceConverter.convertMeterToLongitude(distance);
        return new ScopeMapInfo(startLatitude,startLongitude,endLatitude,endLongitude);
    }
}
