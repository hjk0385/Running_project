package com.trainer.courserunner.course.overseer;

import android.location.Location;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class CourseOverseer extends AsyncTask<Location, Void, Void> implements Observer {
    protected Location currentLocation;
    protected Long usercourseId;

    private List<Observer> subscribeList = new ArrayList<>();

    public CourseOverseer(Long usercourseId) {
        this.usercourseId = usercourseId;
        this.currentLocation = null;
    }

    public void sellSubscription(Observer observer) {
        subscribeList.add(observer);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        subscribeList.stream().forEach((Observer observer) -> observer.update(null, currentLocation));
    }

    @Override
    final public void update(Observable observable, Object o) {
        Location location = (Location) o;
        currentLocation = location;
        this.execute(location);
    }
}
