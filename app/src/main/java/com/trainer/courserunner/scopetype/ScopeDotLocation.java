package com.trainer.courserunner.scopetype;

public class ScopeDotLocation extends ScopeDotAddress{
    double startX;
    double startY;
    double endX;
    double endY;
    public ScopeDotLocation(double startX, double startY, double endX, double endY, double x, double y) {
        super(startX, startY, endX, endY, x, y);
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;
    }
    public void refreshLocation(double latitude,double longtitude){
        this.latitude=latitude;
        this.longitude=longtitude;
        this.normalizeX=normalizeX(this.startX,this.endX,longtitude);
        this.normalizeY=normalizeY(this.startY,this.endY,latitude);
    }
}
