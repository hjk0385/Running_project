package com.trainer.courserunner.course.overseer;

import android.location.Location;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/*
currentlocation 제거
*/
public abstract class CourseOverseer implements Observer {
    protected Long usercourseId;

    private List<Observer> subscribeList = new ArrayList<>();

    public CourseOverseer(Long usercourseId) {
        this.usercourseId = usercourseId;
    }

    public void sellSubscription(Observer observer) {
        subscribeList.add(observer);
    }

    abstract public void oversight(Location location);

    @Override
    final public void update(Observable observable, Object o) {
        new CourseOverseerAsyncTask().execute((Location) o);
    }

    class CourseOverseerAsyncTask extends AsyncTask<Location, Void, Location> {
        @Override
        protected Location doInBackground(Location... locations) {
            Location location = locations[0];
            oversight(location);
            return location;
        }

        @Override
        protected void onPostExecute(Location location) {
            super.onPostExecute(location);
            subscribeList.stream().forEach((Observer observer) -> observer.update(null, location));
        }
    }
}
