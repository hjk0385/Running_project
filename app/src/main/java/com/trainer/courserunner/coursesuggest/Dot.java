package com.trainer.courserunner.coursesuggest;

public abstract class Dot {
    private final double normalizeX;
    private final double normalizeY;

    public Dot(double normalizeX, double normalizeY) {
        this.normalizeX = Math.abs(normalizeX);
        this.normalizeY = Math.abs(normalizeY);
    }

    public double getCost(Dot dot) {
        double xCost = this.normalizeX - dot.normalizeX;
        double yCost = this.normalizeY - dot.normalizeY;
        //z^2=x^2+y^2
        return Math.sqrt(xCost * xCost + yCost * yCost);
    }
}
