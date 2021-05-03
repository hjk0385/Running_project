package com.trainer.courserunner.course;

import android.location.Location;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.CourseDrawerUserCourse;
import com.trainer.courserunner.course.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;

public class CourseConductorSketchBook extends CourseConductor {
    CourseOverseerUserRecord courseOverseerUserRecord;
    CourseDrawerUserCourse courseDrawerUserCourse;
    public CourseConductorSketchBook(MapDrawer mapDrawer) {
        super(mapDrawer);
        //데이터베이스
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        //코스모드 불러오기
        CourseMode courseMode = appDatabase.courseModeDao().getCourseMode("SketchBook");
        //유저코스 변경
        UserCourse userCourse = new UserCourse();
        userCourse.courseId = null;
        userCourse.userCourseId = userCourseId;
        userCourse.courseModeId = courseMode.courseModeId;
        userCourse.userCourseName = null;
        appDatabase.userCourseDao().updateDto(userCourse);
        //
        courseDrawerUserCourse = new CourseDrawerUserCourse(mapDrawer, userCourseId);
        courseOverseerUserRecord = new CourseOverseerUserRecord(userCourseId);
        //설정
        courseOverseerUserRecord.setCurrentLineColor(currentColor);
        //연계
        courseOverseerUserRecord.sellSubscription(courseDrawerUserCourse);
    }

    @Override
    protected void changedLocation(Location location) {
        courseOverseerUserRecord.update(null, location);
    }
}




