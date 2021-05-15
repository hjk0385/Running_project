package com.trainer.courserunner.course.conductor;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.map.drawer.MapDrawer;

public abstract class CourseConductor {
    //정보
    final protected MapDrawer mapDrawer;
    final protected Long userCourseId;
    protected Integer currentColor;

    public CourseConductor(MapDrawer mapDrawer,Long userCourseId){
        this.mapDrawer=mapDrawer;
        this.userCourseId=userCourseId;
        this.currentColor=Color.RED;
    }

    public void setCurrentColor(Integer currentColor) {
        this.currentColor = currentColor;
    }
    abstract public void refreshLocation(Location location);
}
