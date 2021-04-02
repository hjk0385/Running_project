package com.trainer.courserunner.coursesuggest;

public class DotRGB extends Dot {
    public DotRGB(int imageWidth, int imageHeight, int x, int y) {
        super(normalizeX(imageWidth, x), normalizeY(imageHeight, y));
    }

    private static double normalizeX(int imageWidth, int x) {
        return (double) x / imageWidth;
    }

    private static double normalizeY(int imageHeight, int y) {
        return 1 - ((double) y / imageHeight);
    }
}