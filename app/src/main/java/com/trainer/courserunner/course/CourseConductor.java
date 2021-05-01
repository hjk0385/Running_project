package com.trainer.courserunner.course;

import android.location.Location;

import com.trainer.courserunner.map.geo.DistanceConverter;

import java.util.Observer;

public abstract class CourseConductor implements Observer {
    protected boolean checkUpdateDistance(Location location1,Location location2) {
        final Double UPDATE_DISTANCE = 100.0;
        return DistanceConverter.getDistance(location1.getLatitude(), location1.getLongitude(),
                location2.getLatitude(), location2.getLongitude()) >= UPDATE_DISTANCE;
    }
}
