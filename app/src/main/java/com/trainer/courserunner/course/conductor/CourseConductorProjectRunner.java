package com.trainer.courserunner.course.conductor;

import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorProjectRunner extends CourseConductorGuideRunner {
    public CourseConductorProjectRunner(MapDrawer mapDrawer, Long courseId, Long userCourseId) {
        super(mapDrawer, courseId, userCourseId);
    }
}
