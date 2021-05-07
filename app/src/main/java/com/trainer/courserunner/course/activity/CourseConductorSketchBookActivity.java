package com.trainer.courserunner.course.activity;

import android.content.Intent;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;
import com.trainer.courserunner.course.CourseConductorSketchBook;

public class CourseConductorSketchBookActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder=new CourseConductorBuilder(this);
        Intent intent =getIntent();
        String createType=intent.getStringExtra("CreateType");
        if(createType.equals("New")){
            return courseConductorBuilder.buildNew("SketchBook");
        }
        else if(createType.equals("Resume")){
            Long userCourseId=intent.getLongExtra("userCourseId",-1);
            courseConductorBuilder.setUserCourseId(userCourseId);
            return courseConductorBuilder.buildResume("SketchBook");
        }
        return null;
    }
}
