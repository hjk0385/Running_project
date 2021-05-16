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

    boolean percentFlag25;
    boolean percentFlag50;
    boolean percentFlag75;
    boolean percentFlag100;

    @Override
    protected Object runInWorkThread() {
        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();

        /*
            0~100 : 25, 50, 75, 100
            0~50 : 12.5, 25, 37.5 ,50
            0~1 : 0.25, 0.5, 0.75 1
            0~n : (int)0.25*n 0.5*n 0.75*n 1*n

            ex)
            0~12 : 3, 6, 9 ,12
        */

        int flagCount=appDatabase.courseFlagDao().getCountCourseMarkerFlags(courseId);
        int passedFlagCount=UserCourseFlagDerived.getCountUnvistedUserCourseFlags(courseId,userCoursedId);







        return null;
    }

    protected void runInUiThread(Object object){
        super.runInUiThread(object);
        ((SoundCommand)object).execute();
    }
}
