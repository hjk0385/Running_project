package com.trainer.courserunner.course.maker.scopetype;

public class ScopeDotPixel extends ScopeDot {
    public ScopeDotPixel(Integer imageWidth, Integer imageHeight, Integer x, Integer y) {
        super(normalizeX(imageWidth, x), normalizeY(imageHeight, y));
    }

    private static double normalizeX(Integer imageWidth, Integer x) {
        return (double) x / imageWidth;
    }

    private static double normalizeY(Integer imageHeight, Integer y) {
        return 1 - ((double) y / imageHeight);
    }
}