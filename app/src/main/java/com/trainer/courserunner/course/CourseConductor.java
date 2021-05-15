package com.trainer.courserunner.course;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.map.geo.DistanceConverter;

import java.util.Observable;
import java.util.Observer;

public abstract class CourseConductor implements Observer {
    Long userCourseId;
    MapDrawer mapDrawer;
    Integer currentColor = Color.RED;

    public CourseConductor(MapDrawer mapDrawer, Long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.userCourseId = userCourseId;
    }


    protected abstract void changedLocation(Location location);

    public void setCurrentColor(Integer currentColor) {
        this.currentColor = currentColor;
    }
}
