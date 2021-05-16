package com.trainer.courserunner.course.conductor;

import android.preference.ListPreference;
import android.preference.PreferenceManager;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.Application.sound.VoiceType;
import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideLine;
import com.trainer.courserunner.course.component.drawer.CourseDrawerGuideMarker;
import com.trainer.courserunner.course.component.sounder.CourseSounderGuide;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorGuideRunner extends CourseConductorSketchBook {
    Long courseId;
    CourseDrawer drawerGuideLine;
    CourseDrawer drawerGuideMarker;
    CourseSounderGuide courseSounderGuide;


    public CourseConductorGuideRunner(MapDrawer mapDrawer, @NotNull Long courseId, Long userCourseId) {
        super(mapDrawer, userCourseId);
        this.courseId = courseId;

        drawerGuideLine = new CourseDrawerGuideLine(mapDrawer,courseId);
        drawerGuideMarker = new CourseDrawerGuideMarker(mapDrawer, courseId, userCourseId);
        courseSounderGuide = new CourseSounderGuide(courseId,userCourseId);

        overseerUserRecord.setFinishEventConsumer((Object o)->{
            drawerUserRecord.runComponent();
            drawerGuideLine.runComponent();
            drawerGuideMarker.runComponent();
            courseSounderGuide.setVoiceType(VoiceType.FEMALE);
            courseSounderGuide.runComponent();
        });
    }
}
