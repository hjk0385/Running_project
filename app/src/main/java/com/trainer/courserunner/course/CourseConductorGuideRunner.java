package com.trainer.courserunner.course;

import android.location.Location;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.course.drawer.CourseDrawer;
import com.trainer.courserunner.course.drawer.CourseDrawerGuideLine;
import com.trainer.courserunner.course.drawer.CourseDrawerGuideMarker;
import com.trainer.courserunner.course.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.course.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorGuideRunner extends CourseConductor {
    Long courseId;
    CourseOverseerUserRecord courseOverseerUserRecord;
    CourseDrawer courseDrawerUserRecord;
    CourseDrawer courseDrawerGuideLine;
    CourseDrawer courseDrawerGuideMarker;

    public CourseConductorGuideRunner(MapDrawer mapDrawer, @NotNull Long courseId, Long userCourseId) {
        super(mapDrawer, userCourseId);
        this.courseId = courseId;
        //생성
        courseOverseerUserRecord = new CourseOverseerUserRecord(userCourseId);
        courseDrawerGuideLine = new CourseDrawerGuideLine(mapDrawer, courseId);
        courseDrawerGuideMarker = new CourseDrawerGuideMarker(mapDrawer, courseId, userCourseId);
        courseDrawerUserRecord = new CourseDrawerUserRecord(mapDrawer, userCourseId);
        //이벤트 연계 설정(Flag -> 유저위치 -> 유저코스그리기 + 코스그리기 + 마커그리기)
        courseOverseerUserRecord.sellSubscription(courseDrawerGuideLine);
        courseOverseerUserRecord.sellSubscription(courseDrawerUserRecord);
        courseOverseerUserRecord.sellSubscription(courseDrawerGuideMarker);
    }

    @Override
    protected void changedLocation(Location location) {
        courseOverseerUserRecord.update(null, location);
    }

    @Override
    public void setCurrentColor(Integer currentColor) {
        super.setCurrentColor(currentColor);
        courseOverseerUserRecord.setCurrentLineColor(currentColor);
    }
}
