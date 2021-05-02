package com.trainer.courserunner.course.maker.scopetype;

public class ScopeDotLocation extends ScopeDotAddress {
    ScopeMapInfo scopeMapInfo;

    public ScopeDotLocation(ScopeMapInfo scopeMapInfo, Double x, Double y) {
        super(scopeMapInfo, x, y);
        this.scopeMapInfo = scopeMapInfo;
    }

    public void refreshLocation(Double latitude, Double longtitude) {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.normalizeX = normalizeX(scopeMapInfo.getStartX(), scopeMapInfo.getEndX(), longtitude);
        this.normalizeY = normalizeY(scopeMapInfo.getStartY(), scopeMapInfo.getEndY(), latitude);
    }
}
