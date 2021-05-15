package com.trainer.courserunner.course.sounder;

import android.os.AsyncTask;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.rooms.UserCourseFlagDerived;

import java.util.Observable;
import java.util.Observer;

public class CourseSounder implements Observer {
    Long courseId;
    Long userCoursedId;

    public CourseSounder(Long courseId, Long userCoursedId) {
        this.courseId = courseId;
        this.userCoursedId = userCoursedId;
    }

    @Override
    final public void update(Observable observable, Object o) {
        new CourseSounderAsyncTask().execute();
    }

    class CourseSounderAsyncTask extends AsyncTask<Void, Void, SoundCommand> {
        @Override
        protected SoundCommand doInBackground(Void... voids) {
            int flagCount = AppDatabaseLoader.getAppDatabase().courseFlagDao().getCountCourseMarkerFlags(courseId);
            int passFlagCount = UserCourseFlagDerived.getCountUnvistedUserCourseFlags(courseId, userCoursedId);
            return new SoundCommandCourseGuide(flagCount, passFlagCount);
        }

        @Override
        protected void onPostExecute(SoundCommand soundCommand) {
            super.onPostExecute(soundCommand);
            soundCommand.execute();
        }
    }
}
