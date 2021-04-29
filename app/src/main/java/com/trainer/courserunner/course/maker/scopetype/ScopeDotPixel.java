package com.trainer.courserunner.course.maker.scopetype;

public class ScopeDotPixel extends ScopeDot {
    public ScopeDotPixel(int imageWidth, int imageHeight, int x, int y) {
        super(normalizeX(imageWidth, x), normalizeY(imageHeight, y));
    }

    private static double normalizeX(int imageWidth, int x) {
        return (double) x / imageWidth;
    }

    private static double normalizeY(int imageHeight, int y) {
        return 1 - ((double) y / imageHeight);
    }
}