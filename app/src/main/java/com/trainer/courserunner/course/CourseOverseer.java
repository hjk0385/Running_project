package com.trainer.courserunner.course;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.ArrayList;
import java.util.List;

public class CourseOverseer {
    //drawer
    MapDrawer mapDrawer;
    //Info
    double startX;
    double startY;
    double endX;
    double endY;
    //Path
    int pathNumber;
    List<ScopeDotAddress> passedPath;
    List<ScopeDotAddress> remainPath;
    //Overlay
    Object overlayPassedPath;
    Object overlayRemainPath;

    public CourseOverseer(MapDrawer mapDrawer, List<ScopeDotAddress> addressList, double startX, double startY, double endX, double endY) {
        //drawer
        this.mapDrawer = mapDrawer;
        //info
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        //path
        this.pathNumber = addressList.size();
        this.passedPath = new ArrayList<>();
        this.remainPath = new ArrayList<>(addressList);
        //overlay
        overlayPassedPath = null;
        overlayRemainPath = null;
        //overlay reset
        refreshRemainPath();
    }

    public void refresh(ScopeDotAddress currentLocation) {
        boolean changed = oversightLocation(currentLocation);
        if (changed) {
            refreshPassedPath();
            refreshRemainPath();
            checkMiddleGoal();
        }
    }

    public void refresh(double longitude, double latitude) {
        refresh(new ScopeDotAddress(startX, startY, endX, endY, longitude, latitude));
    }

    private boolean oversightLocation(ScopeDotAddress currentLocation) {
        if (remainPath.size() == 0) {
            return false;
        }
        ScopeDotAddress currentGoal = remainPath.get(0);
        //0.0001이하의 거리를 가지면 완료된 코스지점으로 바뀐다.
        if (currentGoal.getCost(currentLocation) <= 0.0001) {
            passedPath.add(currentGoal);
            remainPath.remove(currentGoal);
            return true;
        } else {
            return false;
        }
    }

    private void refreshPassedPath() {
        if (overlayPassedPath != null) {
            this.mapDrawer.clearDraw(overlayPassedPath);
            overlayPassedPath = null;
        }
        if (passedPath.size() >= 2) {
            this.mapDrawer.drawPassedPath(passedPath);
        }
    }

    private void refreshRemainPath() {
        if (overlayRemainPath != null) {
            this.mapDrawer.clearDraw(overlayRemainPath);
            overlayRemainPath = null;
        }
        if (remainPath.size() >= 2) {
            this.mapDrawer.drawRemainPath(remainPath);
        }
    }

    private void checkMiddleGoal() {
        int passedPathNumber = passedPath.size();
        if (passedPathNumber == (pathNumber * 1) / 4) {
            //sound 1/4
        }
        if (passedPathNumber == (pathNumber * 2) / 4) {
            //sound 2/4
        }
        if (passedPathNumber == (pathNumber * 3) / 4) {
            //sound 3/4
        }
        if (passedPathNumber == (pathNumber * 4) / 4) {
            //sound 4/4
        }
    }
}
