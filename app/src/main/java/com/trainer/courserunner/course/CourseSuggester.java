package com.trainer.courserunner.course;

import android.graphics.Bitmap;

import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class CourseSuggester {
    static List<ScopeDotAddress> suggestPath(ScopeDotsImage scopeDotsImage,ScopeDotsMap scopeDotsMap){
        List<ScopeDotAddress> course=new ArrayList<>();
        List<ScopeDot> scopeDotList=scopeDotsMap.quantization(scopeDotsImage);
        //최소거리 연산
        ScopeDot currentScopeDot = scopeDotList.get(0);
        scopeDotList.remove(currentScopeDot);
        course.add((ScopeDotAddress)currentScopeDot);
        while (scopeDotList.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(scopeDotList, currentScopeDot);
            scopeDotList.remove(currentScopeDot);
            course.add((ScopeDotAddress)currentScopeDot);
        }
        return course;
    }
}
