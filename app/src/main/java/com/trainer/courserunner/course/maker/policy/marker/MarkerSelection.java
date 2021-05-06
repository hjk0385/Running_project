package com.trainer.courserunner.course.maker.policy.marker;

import com.trainer.courserunner.rooms.CourseFlag;

import java.util.List;

public interface MarkerSelection {
    public List<CourseFlag> selection(List<CourseFlag> courseFlags);
}
