package com.trainer.courserunner.course;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.map.geo.DistanceConverter;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourse;

import java.util.Observable;
import java.util.Observer;

public abstract class CourseConductor implements Observer {
    Long userCourseId;
    MapDrawer mapDrawer;
    Location currentlocation = null;
    Integer currentColor = Color.RED;

    public CourseConductor(MapDrawer mapDrawer,Long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.userCourseId=userCourseId;
    }

    private boolean checkUpdateDistance(Location location1, Location location2) {
        final Double UPDATE_DISTANCE = 10.0;
        return DistanceConverter.getDistance(location1.getLatitude(), location1.getLongitude(),
                location2.getLatitude(), location2.getLongitude()) >= UPDATE_DISTANCE;
    }

    @Override
    final public void update(Observable observable, Object o) {
        Location location = (Location) o;
        if (currentlocation == null || checkUpdateDistance(currentlocation, location)) {
            currentlocation = location;
            changedLocation(location);
        }
    }

    protected abstract void changedLocation(Location location);

    public void setCurrentColor(Integer currentColor) {
        this.currentColor = currentColor;
    }
}
