package com.trainer.courserunner.course.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;

import com.trainer.courserunner.Application.ModeType;
import com.trainer.courserunner.Application.StartType;
import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;
import com.trainer.courserunner.map.drawer.MapDrawer;

public class CourseConductorGuideRunnerActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        StartType startType = (StartType) getIntent().getSerializableExtra("startType");
        ModeType modeType = ModeType.GUIDERUNNER;

        CourseConductorBuilder courseConductorBuilder = new CourseConductorBuilder();
        courseConductorBuilder.setMapDrawer(this);
        courseConductorBuilder.setStartType(startType);
        courseConductorBuilder.setModeType(modeType);
        Long courseId = getIntent().getLongExtra("courseId",-1);
        courseConductorBuilder.setCourseId(courseId);
        if(startType==StartType.RESUME){
            Long userCourseId=getIntent().getLongExtra("userCourseId",-1);
            courseConductorBuilder.setUserCourseId(userCourseId);
        }
        return courseConductorBuilder.build();
    }
}
