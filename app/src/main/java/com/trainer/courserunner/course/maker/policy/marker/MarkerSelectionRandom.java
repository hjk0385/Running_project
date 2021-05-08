package com.trainer.courserunner.course.maker.policy.marker;

public class MarkerSelectionRandom implements MarkerSelection {
    private final double reminderPercentage;

    public MarkerSelectionRandom(double reminderPercentage) {
        this.reminderPercentage = reminderPercentage;
    }

    @Override
    public Boolean get() {
        return reminderPercentage > Math.random();
    }
}
