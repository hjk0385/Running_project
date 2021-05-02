package com.trainer.courserunner;

import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.course.CourseConductorSketchBook;

public class SketchBookActivity extends RunningMapActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        return new CourseConductorSketchBook(this);
    }
}
