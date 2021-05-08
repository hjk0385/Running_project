package com.trainer.courserunner.course;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;

public class CourseConductorBuilder {
    String modeType;
    String startType;
    boolean usedBuilder;
    private Long courseId;
    private Long userCourseId;
    private MapDrawer mapDrawer;

    public CourseConductorBuilder(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
        this.usedBuilder = false;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setUserCourseId(Long userCourseId) {
        this.userCourseId = userCourseId;
    }

    //객체 재사용 금지
    private void banRecycle() {
        if (usedBuilder) {
            throw new IllegalStateException();
        }
        usedBuilder = true;
    }

    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public void setStartType(String startType) {
        this.startType = startType;
    }

    public CourseConductor build() {
        banRecycle();
        if (startType.equals("New")) {
            AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
            CourseMode courseMode = appDatabase.courseModeDao().getCourseMode(modeType);
            //
            UserCourse userCourse = new UserCourse();
            userCourse.courseId = null;
            userCourse.userCourseId = null;
            userCourse.courseModeId = courseMode.courseModeId;
            userCourse.userCourseName = null;
            userCourseId = appDatabase.userCourseDao().insertDto(userCourse);
            //
        }
        switch (modeType) {
            case "SketchBook":
                return new CourseConductorSketchBook(mapDrawer, userCourseId);
            case "GuideRunner":
                return new CourseConductorGuideRunner(mapDrawer, courseId, userCourseId);
            case "ProjectRunner":
                return new CourseConductorProjectRunner(mapDrawer, courseId, userCourseId);
            default:
                throw new IllegalStateException();
        }
    }

}
