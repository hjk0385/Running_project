package com.trainer.courserunner.course;

import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class CourseSuggester {
    static List<ScopeDotAddress> suggestPath(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap,ScopeDotAddress startLocation) {
        //양자화
        ScopeDotsMap quantizationImage = scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage,0.1);
        //깃발과 코스
        List<ScopeDot> flagAddresses= quantizationImage.getScopeDotList();
        List<ScopeDotAddress> course = new ArrayList<>();
        //코스그리기
        course.add((ScopeDotAddress) startLocation);
        ScopeDot currentScopeDot = ScopeDots.getClosestDot(flagAddresses,startLocation);
        while (flagAddresses.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(flagAddresses, currentScopeDot);
            flagAddresses.remove(currentScopeDot);
            course.add((ScopeDotAddress) currentScopeDot);
        }
        course.add(startLocation);
        return course;
    }
}
