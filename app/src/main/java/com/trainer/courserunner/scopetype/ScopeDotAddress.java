package com.trainer.courserunner.scopetype;

public class ScopeDotAddress extends ScopeDot {
    double longitude; //경도(x)
    double latitude; //위도(y)

    public ScopeDotAddress(double startX, double startY,
                           double endX, double endY,
                           double x, double y) {
        super(normalizeX(startX, endX, x), normalizeY(startY, endY, y));
        this.longitude = x;
        this.latitude = y;
    }

    private static double normalizeX(double startX, double endX, double x) {
        double width = endX - startX;
        return (x - startX) / width;
    }

    private static double normalizeY(double startY, double endY, double y) {
        double height = endY - startY;
        return (y - startY) / height;
    }

    // result = m
    public double getCost(ScopeDotAddress scopeDotAddress) {
        //위도 1도 = 111.195km = 111195m
        //경도 1도 = 88.804km = 88804m
        double costLatitude=Math.abs(this.latitude-scopeDotAddress.latitude)*111195;
        double costLongtitude=Math.abs(this.longitude-scopeDotAddress.longitude)*88804;
        return Math.sqrt(costLatitude*costLatitude+costLongtitude*costLongtitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getX() {
        return longitude;
    }

    public double getY() {
        return latitude;
    }
}