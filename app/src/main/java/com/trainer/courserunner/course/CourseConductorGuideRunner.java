package com.trainer.courserunner.course;

import android.location.Location;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.CourseDrawerGuideCourse;
import com.trainer.courserunner.course.drawer.CourseDrawerMarkerGuideCourse;
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
        CourseDrawerUserCourse courseDrawerUserCourse = new CourseDrawerUserCourse(mapDrawer, userCourseId);
        CourseDrawerGuideCourse courseDrawerGuideCourse = new CourseDrawerGuideCourse(mapDrawer, courseId);
        CourseDrawerMarkerGuideCourse courseDrawerMarkerGuideCourse = new CourseDrawerMarkerGuideCourse(mapDrawer,courseId,userCourseId);
        //설정
        courseOverseerUserRecord.setCurrentLineColor(currentColor);
        //실행순서 (Flag -> 유저위치 -> 유저코스그리기 + 코스그리기)
        courseOverseerUserFlag.sellSubscription(courseOverseerUserRecord);
        courseOverseerUserRecord.sellSubscription(courseDrawerUserCourse);
        courseOverseerUserRecord.sellSubscription(courseDrawerGuideCourse);
        courseOverseerUserRecord.sellSubscription(courseDrawerMarkerGuideCourse);
        //실행
        courseOverseerUserFlag.update(null, location);
    }
}
