package com.trainer.courserunner.course;

import com.trainer.courserunner.course.activity.CourseConductorSketchBookActivity;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorProjectRunner extends CourseConductorGuideRunner {
    public CourseConductorProjectRunner(MapDrawer mapDrawer, Long courseId, Long userCourseId) {
        super(mapDrawer, courseId, userCourseId);
    }
}
