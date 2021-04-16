package com.trainer.courserunner.scopetype;

public class ScopeDotAddress extends ScopeDot {
    protected double longitude; //경도(x)
    protected double latitude; //위도(y)

    public ScopeDotAddress(ScopeMapInfo scopeMapInfo,
                           double x, double y) {
        super(normalizeX(scopeMapInfo.getStartX(), scopeMapInfo.getEndX(), x),
                normalizeY(scopeMapInfo.getStartY(), scopeMapInfo.getEndY(), y));
        this.longitude = x;
        this.latitude = y;
    }

    protected static double normalizeX(double startX, double endX, double x) {
        double width = endX - startX;
        return (x - startX) / width;
    }

    protected static double normalizeY(double startY, double endY, double y) {
        double height = endY - startY;
        return (y - startY) / height;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}