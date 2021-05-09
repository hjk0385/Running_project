package com.trainer.courserunner.course.activity;

import android.content.Intent;

import com.trainer.courserunner.Application.ModeType;
import com.trainer.courserunner.Application.StartType;
import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;

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
