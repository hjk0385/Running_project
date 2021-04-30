package com.trainer.courserunner.map.geo;

import android.location.Location;

import com.trainer.courserunner.course.maker.scopetype.ScopeMapInfo;

public class DistanceConverter {
    // result = m
    static public double getDistance(double latitude1, double longitude1,
                                     double latitude2, double longitude2) {
        //위도 1도 = 111.195km = 111195m
        //경도 1도 = 88.804km = 88804m
        double costLatitude = Math.abs(latitude1 - latitude2) * 111195;
        double costLongtitude = Math.abs(longitude1 - longitude2) * 88804;
        return Math.sqrt(costLatitude * costLatitude + costLongtitude * costLongtitude);
    }

    static public double convertMeterToLatitude(double meter) {
        return (double) meter / 111195;
    }

    static public double convertMeterToLongitude(double meter) {
        return (double) meter / 88804;
    }

    static public double convertKiloMeterToLatitude(double kilometer) {
        return convertMeterToLatitude(kilometer * 1000);
    }

    static public double convertKiloMeterToLongitude(double kilometer) {
        return convertMeterToLongitude(kilometer * 1000);
    }

    static public ScopeMapInfo getScopeMapInfo(Location location, double kilometer) {
        double distLatitude = convertKiloMeterToLatitude(kilometer);
        double distLongitude = convertKiloMeterToLongitude(kilometer);

        double startLongitude = location.getLongitude();
        double startLatitude = location.getLatitude();
        double endLongitude = location.getLongitude() + distLongitude;
        double endLatitude = location.getLatitude() + distLatitude;
        return new ScopeMapInfo(startLatitude, startLongitude, endLatitude, endLongitude);
    }

}
