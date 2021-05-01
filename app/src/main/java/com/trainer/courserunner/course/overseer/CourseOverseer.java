package com.trainer.courserunner.course.overseer;

import android.location.Location;
import android.os.AsyncTask;

import com.trainer.courserunner.map.geo.DistanceConverter;

import java.util.Observable;
import java.util.Observer;

public abstract class CourseOverseer extends AsyncTask<Location, Void, Void> implements Observer {
    protected Location currentLocation;
    protected long usercourseId;

    public CourseOverseer(long usercourseId) {
        this.usercourseId = usercourseId;
        this.currentLocation = null;
    }

    protected boolean checkUpdateDistance(Location location) {
        final Double UPDATE_DISTANCE = 100.0;
        return DistanceConverter.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                location.getLatitude(), location.getLongitude()) >= UPDATE_DISTANCE;
    }

    @Override
    final public void update(Observable observable, Object o) {
        Location location = (Location) o;
        if (currentLocation == null || checkUpdateDistance(location)) {
            currentLocation = location;
            this.execute(location);
        }
    }
}
