package com.trainer.courserunner.course.conductor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.Application.sound.VoiceType;
import com.trainer.courserunner.course.component.capture.CourseCapture;
import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideLine;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideMarker;
import com.trainer.courserunner.course.component.sounder.CourseSounderGuide;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.Objects;

public class CourseConductorGuideRunner extends CourseConductorSketchBook {
    Long courseId;
    CourseDrawer drawerGuideLine;
    CourseDrawer drawerGuideMarker;
    CourseSounderGuide courseSounderGuide;


    public CourseConductorGuideRunner(MapDrawer mapDrawer, @NotNull Long courseId, Long userCourseId, Context context) {
        super(mapDrawer, userCourseId, context);
        this.courseId = courseId;

        drawerGuideLine = new CourseDrawerGuideLine(mapDrawer, courseId);
        drawerGuideMarker = new CourseDrawerGuideMarker(mapDrawer, courseId, userCourseId);
        courseSounderGuide = new CourseSounderGuide(courseId, userCourseId,context);

        overseerUserRecord.setFinishEventConsumer((Object o) -> {
            drawerUserRecord.runComponent();
            drawerGuideLine.runComponent();
            drawerGuideMarker.runComponent();
            courseSounderGuide.runComponent();

            courseCapture.setScreenshotName(String.valueOf(o));
            courseCapture.runComponent();
        });
    }
}
