package com.trainer.courserunner.runactivity.run;

import com.trainer.courserunner.Application.trainertype.ModeType;
import com.trainer.courserunner.conductor.CourseConductor;
import com.trainer.courserunner.conductor.CourseConductorBuilder;

public class SketchBookRunnerActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder = getDefaultCourseConductorBuilder();
        courseConductorBuilder.setModeType(ModeType.SKETCHBOOK);
        return courseConductorBuilder.build();
    }
}
