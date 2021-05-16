package com.trainer.courserunner.course.conductor.activity;

import com.trainer.courserunner.Application.enumtype.ModeType;
import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.course.conductor.CourseConductor;
import com.trainer.courserunner.course.conductor.CourseConductorBuilder;

public class CourseConductorSketchBookActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        StartType startType = (StartType) getIntent().getSerializableExtra("startType");
        ModeType modeType = ModeType.SKETCHBOOK;

        CourseConductorBuilder courseConductorBuilder = new CourseConductorBuilder();
        courseConductorBuilder.setMapDrawer(this);
        courseConductorBuilder.setStartType(startType);
        courseConductorBuilder.setModeType(modeType);
        if(startType==StartType.RESUME){
            Long userCourseId=getIntent().getLongExtra("userCourseId",-1);
            courseConductorBuilder.setUserCourseId(userCourseId);
        }
        return courseConductorBuilder.build();
    }
}