package com.trainer.courserunner.course;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;

public class CourseConductorBuilder {
    private Long courseId;
    private Long userCourseId;
    private MapDrawer mapDrawer;
    boolean usedBuilder;

    public CourseConductorBuilder(MapDrawer mapDrawer){
        this.mapDrawer=mapDrawer;
        this.usedBuilder=false;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public void setUserCourseId(Long userCourseId) {
        this.userCourseId = userCourseId;
    }

    //객체 재사용 금지
    private void banRecycle(){
        if(usedBuilder){
            throw new IllegalArgumentException();
        }
        usedBuilder=true;
    }

    private CourseConductor build(String courseModeName){
        switch (courseModeName){
            case "SketchBook":
                return new CourseConductorSketchBook(mapDrawer,userCourseId);
            case "GuideRunner":
                return new CourseConductorGuideRunner(mapDrawer,courseId,userCourseId);
            default:
                return null;
        }
    }

    public CourseConductor buildNew(String courseModeName){
        banRecycle();
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        CourseMode courseMode = appDatabase.courseModeDao().getCourseMode(courseModeName);
        //
        UserCourse userCourse = new UserCourse();
        userCourse.courseId = null;
        userCourse.userCourseId = null;
        userCourse.courseModeId = courseMode.courseModeId;
        userCourse.userCourseName = null;
        userCourseId=appDatabase.userCourseDao().insertDto(userCourse);
        //
        return build(courseModeName);
    }

    public CourseConductor buildResume(String courseModeName){
        banRecycle();
        return build(courseModeName);
    }
}
