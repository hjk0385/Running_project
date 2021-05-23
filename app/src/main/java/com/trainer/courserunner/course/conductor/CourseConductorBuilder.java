package com.trainer.courserunner.course.conductor;

import android.content.Context;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.Application.enumtype.ModeType;
import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourse;

public class CourseConductorBuilder {
    boolean usedBuilder;
    private MapDrawer mapDrawer;
    private Long courseId;
    private Long userCourseId;
    private ModeType modeType;
    private StartType startType;
    private Context context;

    public CourseConductorBuilder() {
        this.usedBuilder = false;
        this.mapDrawer = null;
        this.courseId = null;
        this.userCourseId = null;
        this.modeType = null;
        this.startType = null;
    }

    private void banRecycle() {
        if (usedBuilder) {
            throw new IllegalStateException();
        }
        usedBuilder = true;
    }

    public void setMapDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setUserCourseId(Long userCourseId) {
        this.userCourseId = userCourseId;
    }

    public void setModeType(ModeType modeType) {
        this.modeType = modeType;
    }

    public void setStartType(StartType startType) {
        this.startType = startType;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CourseConductor build() {
        banRecycle();
        if (mapDrawer == null || modeType == null || startType == null || context == null) {
            throw new IllegalArgumentException();
        }

        if (startType == StartType.NEW) {
            AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();
            UserCourse userCourse = new UserCourse();
            userCourse.courseId = courseId;
            userCourse.userCourseId = null;
            userCourse.courseModeId = (long) modeType.ordinal();
            userCourse.userCourseName = null;
            userCourseId = appDatabase.userCourseDao().insertDto(userCourse);
        }

        switch (modeType) {
            case SKETCHBOOK:
                return new CourseConductorSketchBook(mapDrawer, userCourseId, context);
            case GUIDERUNNER:
                return new CourseConductorGuideRunner(mapDrawer, courseId, userCourseId, context);
            case PROJECTRUNNER:
                return new CourseConductorProjectRunner(mapDrawer, courseId, userCourseId, context);
            default:
                throw new IllegalStateException("Unexpected value: " + modeType);
        }
    }
}
