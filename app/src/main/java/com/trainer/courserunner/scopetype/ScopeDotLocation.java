package com.trainer.courserunner.scopetype;

public class ScopeDotLocation extends ScopeDotAddress {
    ScopeMapInfo scopeMapInfo;

    public ScopeDotLocation(ScopeMapInfo scopeMapInfo, double x, double y) {
        super(scopeMapInfo, x, y);
        this.scopeMapInfo = scopeMapInfo;
    }

    public void refreshLocation(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longitude = longtitude;
        this.normalizeX = normalizeX(scopeMapInfo.getStartX(), scopeMapInfo.getEndX(), longtitude);
        this.normalizeY = normalizeY(scopeMapInfo.getStartY(), scopeMapInfo.getEndY(), latitude);
    }
}
