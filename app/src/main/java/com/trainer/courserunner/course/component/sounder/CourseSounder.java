package com.trainer.courserunner.course.component.sounder;

import android.os.AsyncTask;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.component.CourseComponent;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseFlagDerived;

import java.util.Observable;
import java.util.Observer;

public class CourseSounder extends CourseComponent {
    Long courseId;
    Long userCoursedId;

    public CourseSounder(Long courseId, Long userCoursedId) {
        this.courseId = courseId;
        this.userCoursedId = userCoursedId;
    }

    @Override
    protected Object runInWorkThread() {
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();

        int flagCount=appDatabase.courseFlagDao().getCountCourseMarkerFlags(courseId);
        int passedFlagCount=UserCourseFlagDerived.getCountUnvistedUserCourseFlags(courseId,userCoursedId);

        return new SoundCommandCourseFlag(flagCount,passedFlagCount);
    }

    protected void runInUiThread(Object object){
        super.runInUiThread(object);
        ((SoundCommand)object).execute();
    }
}
