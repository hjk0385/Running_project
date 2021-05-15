package com.trainer.courserunner.course.activity;

import com.trainer.courserunner.Application.ModeType;
import com.trainer.courserunner.Application.StartType;
import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;

public class CourseConductorProjectRunnerActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        StartType startType = (StartType) getIntent().getSerializableExtra("startType");
        ModeType modeType = ModeType.PROJECTRUNNER;

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