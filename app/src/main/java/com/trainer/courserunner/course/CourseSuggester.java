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
        ScopeDotsMap quantizationImage=scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage);
        return quantizationImage.getShortestPath();
    }
}
