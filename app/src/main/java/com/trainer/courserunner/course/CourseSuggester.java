package com.trainer.courserunner.course;

import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.List;

public class CourseSuggester {
    static List<ScopeDotAddress> suggestPath(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap) {
        //ScopeDotsMap quantizationImage = scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage);
        //test
        
        ScopeDotsMap quantizationImage = scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage,0.1);
        return quantizationImage.getShortestPath();
    }
}
