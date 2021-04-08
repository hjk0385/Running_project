package com.trainer.courserunner.course;

import android.graphics.Bitmap;

import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.List;

public class CourseSuggester {
    private List<ScopeDotAddress> course;

    public CourseSuggester(Bitmap image,
                           double startX, double startY,
                           double endX, double endY) {
        ScopeDotsImage dotsImage = new ScopeDotsImage(image);
        ScopeDotsMap dotsScopeMap = new ScopeDotsMap(startX, startY, endX, endY);
        //make course
        List<ScopeDot> scopeScopeDots = dotsImage.quantization(dotsScopeMap);
        ScopeDot currentScopeDot = scopeScopeDots.get(0);
        scopeScopeDots.remove(currentScopeDot);
        course.add((ScopeDotAddress) currentScopeDot);
        while (scopeScopeDots.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(scopeScopeDots, currentScopeDot);
            scopeScopeDots.remove(currentScopeDot);
            course.add((ScopeDotAddress) currentScopeDot);
        }
    }

    //코스 제안
    public List<ScopeDotAddress> suggestPath() {
        return course;
    }
}
