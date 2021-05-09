package com.trainer.courserunner.course;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.Application.ModeType;
import com.trainer.courserunner.Application.StartType;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;

public class CourseConductorBuilder {
    boolean usedBuilder;
    private void banRecycle() {
        if (usedBuilder) {
            throw new IllegalStateException();
        }
        usedBuilder = true;
    }

    private MapDrawer mapDrawer;
    private Long courseId;
    private Long userCourseId;
    private ModeType modeType;
    private StartType startType;

    public CourseConductorBuilder(){
        this.usedBuilder=false;
        this.mapDrawer=null;
        this.courseId=null;
        this.userCourseId=null;
        this.modeType=null;
        this.startType=null;
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

    public CourseConductor build(){
        banRecycle();
        if(mapDrawer==null||modeType==null||startType==null){
            throw new IllegalArgumentException();
        }

        if(startType==StartType.NEW) {
            AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
            UserCourse userCourse = new UserCourse();
            userCourse.courseId = null;
            userCourse.userCourseId = null;
            userCourse.courseModeId = (long) modeType.ordinal();
            userCourse.userCourseName = null;
            userCourseId = appDatabase.userCourseDao().insertDto(userCourse);
        }

        switch (modeType){
            case SKETCHBOOK:
                return new CourseConductorSketchBook(mapDrawer, userCourseId);
            case GUIDERUNNER:
                return new CourseConductorGuideRunner(mapDrawer,courseId,userCourseId);
            case PROJECTRUNNER:
                return new CourseConductorProjectRunner(mapDrawer, courseId, userCourseId);
            default:
                throw new IllegalStateException("Unexpected value: " + modeType);
        }
    }
}
