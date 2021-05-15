package com.trainer.courserunner.course.component.sounder;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.course.component.CourseComponent;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseFlagDerived;

public class CourseSounder extends CourseComponent {
    Long courseId;
    Long userCoursedId;

    public CourseSounder(Long courseId, Long userCoursedId) {
        this.courseId = courseId;
        this.userCoursedId = userCoursedId;
    }

    @Override
    protected Object runInWorkThread() {
        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();

        int flagCount=appDatabase.courseFlagDao().getCountCourseMarkerFlags(courseId);
        int passedFlagCount=UserCourseFlagDerived.getCountUnvistedUserCourseFlags(courseId,userCoursedId);

        return new SoundCommandCourseFlag(flagCount,passedFlagCount);
    }

    protected void runInUiThread(Object object){
        super.runInUiThread(object);
        ((SoundCommand)object).execute();
    }
}
