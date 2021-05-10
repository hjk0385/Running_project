package com.trainer.courserunner.course.maker.scopetype;

public class ScopeDot {
    protected Double normalizeX;
    protected Double normalizeY;

    public ScopeDot(Double normalizeX, Double normalizeY) {
        this.normalizeX = Math.abs(normalizeX);
        this.normalizeY = Math.abs(normalizeY);
    }

    public Double getCost(ScopeDot scopeDot) {
        Double xCost = this.normalizeX - scopeDot.normalizeX;
        Double yCost = this.normalizeY - scopeDot.normalizeY;
        return Math.sqrt(xCost * xCost + yCost * yCost);
    }

    public Double getNormalizeX() {
        return normalizeX;
    }

    public Double getNormalizeY() {
        return normalizeY;
    }
}
