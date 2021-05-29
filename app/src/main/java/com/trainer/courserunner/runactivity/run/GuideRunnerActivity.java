package com.trainer.courserunner.runactivity.run;

import com.trainer.courserunner.trainertype.ModeType;
import com.trainer.courserunner.conductor.CourseConductor;
import com.trainer.courserunner.conductor.CourseConductorBuilder;

public class GuideRunnerActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder = getDefaultCourseConductorBuilder();
        courseConductorBuilder.setModeType(ModeType.GUIDERUNNER);
        return courseConductorBuilder.build();
    }
}
