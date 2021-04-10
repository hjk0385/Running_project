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