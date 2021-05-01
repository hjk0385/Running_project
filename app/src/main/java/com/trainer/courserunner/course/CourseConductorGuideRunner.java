package com.trainer.courserunner.course;

import android.location.Location;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.CourseDrawerUserCourse;
import com.trainer.courserunner.course.overseer.CourseOverseerUserFlag;
import com.trainer.courserunner.course.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;

public class CourseConductorGuideRunner extends CourseConductor {
    Long courseId;

    public CourseConductorGuideRunner(MapDrawer mapDrawer, @NotNull Long courseId) {
        super(mapDrawer);
        //데이터베이스
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        //코스모드 불러오기
        CourseMode courseMode = appDatabase.courseModeDao().getCourseMode("GuideRunner");
        //유저코스 변경
        UserCourse userCourse = new UserCourse();
        userCourse.courseId = courseId;
        userCourse.userCourseId = userCourseId;
        userCourse.courseModeId = courseMode.courseModeId;
        userCourse.userCourseName = null;
        appDatabase.userCourseDao().updateDto(userCourse);
        //
        this.courseId = courseId;
    }

    @Override
    protected void changedLocation(Location location) {
        CourseOverseerUserFlag courseOverseerUserFlag = new CourseOverseerUserFlag(courseId, userCourseId);
        CourseOverseerUserRecord courseOverseerUserRecord = new CourseOverseerUserRecord(userCourseId);
        courseOverseerUserRecord.setCurrentLineColor(currentColor);
        CourseDrawerUserCourse courseDrawerUserCourse = new CourseDrawerUserCourse(mapDrawer, userCourseId);
        //실행순서 (Flag -> 유저위치 -> 코스그리기)
        courseOverseerUserFlag.sellSubscription(courseOverseerUserRecord);
        courseOverseerUserRecord.sellSubscription(courseDrawerUserCourse);
        //실행
        courseOverseerUserFlag.update(null, location);
    }
}
