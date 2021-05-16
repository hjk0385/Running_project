package com.trainer.courserunner.course.conductor;

import android.content.Context;

import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorProjectRunner extends CourseConductorGuideRunner {
    public CourseConductorProjectRunner(MapDrawer mapDrawer, Long courseId, Long userCourseId, Context context) {
        super(mapDrawer, courseId, userCourseId,context);
    }
}
