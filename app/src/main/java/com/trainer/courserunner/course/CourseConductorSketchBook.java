package com.trainer.courserunner.course;

import android.location.Location;

import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.course.component.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorSketchBook extends CourseConductor {
    CourseOverseerUserRecord courseOverseerUserRecord;
    CourseDrawer courseDrawerUserCourse;

    public CourseConductorSketchBook(MapDrawer mapDrawer, Long userCourseId) {
        super(mapDrawer, userCourseId);
        courseDrawerUserCourse = new CourseDrawerUserRecord(mapDrawer, userCourseId);
        courseOverseerUserRecord = new CourseOverseerUserRecord(userCourseId);
        //
        courseOverseerUserRecord.setCurrentLineColor(currentColor);
        //연계
        courseOverseerUserRecord.sellSubscription(courseDrawerUserCourse);
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
