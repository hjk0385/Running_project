package com.trainer.courserunner.maps;

import com.trainer.courserunner.scopetype.ScopeDotAddress;

public class MapFunction {
    // result = m
    static public double getCost(double latitude1, double longitude1,
                                 double latitude2, double longitude2) {
        //위도 1도 = 111.195km = 111195m
        //경도 1도 = 88.804km = 88804m
        double costLatitude=Math.abs(latitude1-longitude1)*111195;
        double costLongtitude=Math.abs(latitude2-longitude2)*88804;
        return Math.sqrt(costLatitude*costLatitude+costLongtitude*costLongtitude);
    }
}
