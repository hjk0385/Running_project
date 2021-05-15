package com.trainer.courserunner.course;

import android.location.Location;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideLine;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideMarker;
import com.trainer.courserunner.course.component.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.course.component.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorGuideRunner extends CourseConductorSketchBook {
    Long courseId;
    CourseDrawer drawerGuideLine;
    CourseDrawer drawerGuideMarker;

    public CourseConductorGuideRunner(MapDrawer mapDrawer, @NotNull Long courseId, Long userCourseId) {
        super(mapDrawer, userCourseId);
        this.courseId = courseId;

        drawerGuideLine = new CourseDrawerGuideLine(mapDrawer,courseId);
        drawerGuideMarker = new CourseDrawerGuideMarker(mapDrawer, courseId, userCourseId);

        overseerUserRecord.setFinishEventConsumer((Object o)->{
            drawerUserRecord.runComponent();
            drawerGuideLine.runComponent();
            drawerGuideMarker.runComponent();
        });
    }
}
