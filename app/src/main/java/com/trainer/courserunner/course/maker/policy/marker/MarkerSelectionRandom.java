package com.trainer.courserunner.course.maker.policy.marker;

import com.trainer.courserunner.rooms.CourseFlag;

import java.util.ArrayList;
import java.util.List;

public class MarkerSelectionRandom implements MarkerSelection {
    private final double reminderPercentage;
    public MarkerSelectionRandom(double reminderPercentage) {
        this.reminderPercentage=reminderPercentage;
    }
    @Override
    public Boolean get() {
        return reminderPercentage > Math.random();
    }
}
