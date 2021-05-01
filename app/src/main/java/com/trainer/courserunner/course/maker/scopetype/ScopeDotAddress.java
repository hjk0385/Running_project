package com.trainer.courserunner.course.maker.scopetype;

public class ScopeDotAddress extends ScopeDot {
    protected Double longitude; //경도(x)
    protected Double latitude; //위도(y)

    public ScopeDotAddress(ScopeMapInfo scopeMapInfo,
                           Double x, Double y) {
        super(normalizeX(scopeMapInfo.getStartX(), scopeMapInfo.getEndX(), x),
                normalizeY(scopeMapInfo.getStartY(), scopeMapInfo.getEndY(), y));
        this.longitude = x;
        this.latitude = y;
    }

    protected static Double normalizeX(Double startX, Double endX, Double x) {
        Double width = endX - startX;
        return (x - startX) / width;
    }

    protected static Double normalizeY(Double startY, Double endY, Double y) {
        Double height = endY - startY;
        return (y - startY) / height;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

}