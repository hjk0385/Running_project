package com.trainer.courserunner.courseguider;

import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.ArrayList;
import java.util.List;

public class CourseOverseer {
    //Info
    MapDrawer mapDrawer;
    double startX;
    double startY;
    double endX;
    double endY;
    //Path
    int pathNumber;
    List<DotAddress> passedPath;
    List<DotAddress> remainPath;
    //Overlay
    Object overlayPassedPath;
    Object overlayRemainPath;

    public CourseOverseer(MapDrawer mapDrawer, List<DotAddress> addressList, double startX, double startY, double endX, double endY) {
        //info
        this.mapDrawer = mapDrawer;
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

    public void refresh(DotAddress currentLocation) {
        boolean changed = oversightLocation(currentLocation);
        if (changed) {
            refreshPassedPath();
            refreshRemainPath();
            checkMiddleGoal();
        }
    }

    public void refresh(double longitude, double latitude) {
        refresh(new DotAddress(startX, startY, endX, endY, longitude, latitude));
    }

    private boolean oversightLocation(DotAddress currentLocation) {
        if (remainPath.size() == 0) {
            return false;
        }
        DotAddress currentGoal = remainPath.get(0);
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
