package com.trainer.courserunner.course.activity;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorSketchBook;

public class CourseConductorSketchBookActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        return new CourseConductorSketchBook(this);
    }
}
