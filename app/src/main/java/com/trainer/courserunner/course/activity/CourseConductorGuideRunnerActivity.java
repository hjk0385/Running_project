package com.trainer.courserunner.course.activity;

import android.content.Intent;
import android.os.Bundle;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorGuideRunner;

public class CourseConductorGuideRunnerActivity extends CourseConductorActivity {
    Long courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();



        courseId = intent.getExtras().getLong("courseId");
    }

    @Override
    protected CourseConductor createCourseConductor() {
        return new CourseConductorGuideRunner(this, courseId);
    }
}
