package com.trainer.courserunner.course.activity;

import android.content.Intent;
import android.os.Bundle;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;
import com.trainer.courserunner.course.CourseConductorGuideRunner;

import java.util.Objects;

public class CourseConductorGuideRunnerActivity extends CourseConductorActivity {
    Long courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseId = getIntent().getExtras().getLong("courseId");
    }

    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder=new CourseConductorBuilder(this);
        courseConductorBuilder.setCourseId(courseId);
        Intent intent =getIntent();
        String createType=intent.getStringExtra("CreateType");
        if(createType.equals("New")){
            return courseConductorBuilder.buildNew("GuideRunner");
        }
        else if(createType.equals("Resume")){
            Long userCourseId=intent.getLongExtra("userCourseId",-1);
            courseConductorBuilder.setUserCourseId(userCourseId);
            return courseConductorBuilder.buildResume("GuideRunner");
        }
        return null;
    }
}
