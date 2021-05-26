package com.trainer.courserunner.course.activity;

import com.trainer.courserunner.Application.enumtype.ModeType;
import com.trainer.courserunner.course.conductor.CourseConductor;
import com.trainer.courserunner.course.conductor.CourseConductorBuilder;

public class GuideRunnerActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder = getDefaultCourseConductorBuilder();
        courseConductorBuilder.setModeType(ModeType.GUIDERUNNER);
        return courseConductorBuilder.build();
    }
}
