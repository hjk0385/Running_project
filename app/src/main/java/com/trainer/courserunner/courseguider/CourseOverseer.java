package com.trainer.courserunner.courseguider;

import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.ArrayList;
import java.util.List;

public class CourseOverseer {
    MapDrawer mapDrawer;
    Object overlayPassedPath;
    Object overlayRemainPath;
    List<DotAddress> passedPath;
    List<DotAddress> remainPath;
    int pathNumber;

    public CourseOverseer(MapDrawer mapDrawer, List<DotAddress> addressList)
    {
        this.mapDrawer=mapDrawer;
        pathNumber=addressList.size();
        passedPath=new ArrayList<>();
        remainPath=new ArrayList<>(addressList);
        overlayPassedPath=null;
        overlayRemainPath=null;
        refreshRemainPath();
    }

    public void refresh(DotAddress currentLocation)
    {
        boolean changed = oversightLocation(currentLocation);
        if(changed){
            refreshPassedPath();
            refreshRemainPath();
            checkMiddleGoal();
        }
    }

    private boolean oversightLocation(DotAddress currentLocation){
        if(remainPath.size()==0){
            return false;
        }
        DotAddress currentGoal= remainPath.get(0);
        if(currentGoal.getCost(currentLocation)<=0.0001)
        {
            passedPath.add(currentGoal);
            remainPath.remove(currentGoal);
            return true;
        }
        else {
            return false;
        }
    }

    private void refreshPassedPath()
    {
        if(overlayPassedPath!=null){
            this.mapDrawer.clearDraw(overlayPassedPath);
            overlayPassedPath=null;
        }
        if(passedPath.size()>=2){
            this.mapDrawer.drawPassedPath(passedPath);
        }
    }

    private void refreshRemainPath()
    {
        if(overlayRemainPath!=null){
            this.mapDrawer.clearDraw(overlayRemainPath);
            overlayRemainPath=null;
        }
        if(remainPath.size()>=2){
            this.mapDrawer.drawRemainPath(remainPath);
        }
    }

    private void checkMiddleGoal(){
        int passedPathNumber=passedPath.size();
        if(passedPathNumber==(pathNumber*1)/4){
            //sound 1/4
        }
        if(passedPathNumber==(pathNumber*2)/4){
            //sound 2/4
        }
        if(passedPathNumber==(pathNumber*3)/4){
            //sound 3/4
        }
        if(passedPathNumber==(pathNumber*4)/4){
            //sound 4/4
        }
    }
}
