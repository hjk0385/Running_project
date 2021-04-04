package com.trainer.courserunner.coursesuggest;

import android.graphics.Bitmap;

import java.util.List;

public class CourseSuggester {
    private List<DotAddress> course;

    public CourseSuggester(Bitmap image,
                           double startX, double startY,
                           double endX, double endY) {
        DotsImage dotsImage = new DotsImage(image);
        DotsScopeMap dotsScopeMap = new DotsScopeMap(startX, startY, endX, endY);
        //make course
        List<Dot> scopeDots = dotsImage.quantization(dotsScopeMap);
        Dot currentDot = scopeDots.get(0);
        scopeDots.remove(currentDot);
        course.add((DotAddress) currentDot);
        while (scopeDots.size() != 0) {
            currentDot = Dots.getClosestDot(scopeDots, currentDot);
            scopeDots.remove(currentDot);
            course.add((DotAddress) currentDot);
        }
    }

    //코스 제안
    public List<DotAddress> suggestPath() {
        return course;
    }
}
