package com.trainer.courserunner.scopetype;

public abstract class ScopeDot {
    private final double normalizeX;
    private final double normalizeY;

    public ScopeDot(double normalizeX, double normalizeY) {
        this.normalizeX = Math.abs(normalizeX);
        this.normalizeY = Math.abs(normalizeY);
    }

    public double getCost(ScopeDot scopeDot) {
        double xCost = this.normalizeX - scopeDot.normalizeX;
        double yCost = this.normalizeY - scopeDot.normalizeY;
        return Math.sqrt(xCost * xCost + yCost * yCost);
    }

    public double getNormalizeX() {
        return normalizeX;
    }

    public double getNormalizeY() {
        return normalizeY;
    }
}
