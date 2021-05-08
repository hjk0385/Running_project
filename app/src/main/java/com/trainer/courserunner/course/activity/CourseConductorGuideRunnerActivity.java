package com.trainer.courserunner.course.activity;

import android.content.Intent;
import android.os.Bundle;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorBuilder;

public class CourseConductorGuideRunnerActivity extends CourseConductorActivity {
    Long courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseId = getIntent().getExtras().getLong("courseId");
    }

    @Override
    protected CourseConductor createCourseConductor() {
        Intent intent = getIntent();

        CourseConductorBuilder courseConductorBuilder = new CourseConductorBuilder(this);
        courseConductorBuilder.setModeType("GuideRunner");
        courseConductorBuilder.setCourseId(courseId);
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
