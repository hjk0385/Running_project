package com.trainer.courserunner.maps;

import com.trainer.courserunner.scopetype.ScopeDotAddress;

public class MapFunction {
    // result = m
    static public double getDistance(double latitude1, double longitude1,
                                 double latitude2, double longitude2) {
        //위도 1도 = 111.195km = 111195m
        //경도 1도 = 88.804km = 88804m
        double costLatitude = Math.abs(latitude1 - latitude2) * 111195;
        double costLongtitude = Math.abs(longitude1 - longitude2) * 88804;
        return Math.sqrt(costLatitude * costLatitude + costLongtitude * costLongtitude);
    }
}
