package com.trainer.courserunner.course.activity;

import android.content.Intent;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;

public class CourseConductorSketchBookActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder = new CourseConductorBuilder(this);
        courseConductorBuilder.setModeType("SketchBook");

        Intent intent = getIntent();
        String startType = intent.getStringExtra("startType");
        if (startType.equals("New")) {
            courseConductorBuilder.setStartType("New");
        } else if (startType.equals("Resume")) {
            courseConductorBuilder.setUserCourseId(intent.getLongExtra("userCourseId", -1));
            courseConductorBuilder.setStartType("Resume");
        } else {
            throw new IllegalArgumentException();
        }
        return courseConductorBuilder.build();
    }
}
