package com.trainer.courserunner.course;

import com.trainer.courserunner.drawing.DrawingPath;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class CourseSuggester {




    public static List<CoursePath> suggestPath(ScopeDotsImage scopeDotsImage,
                                               ScopeDotsMap scopeDotsMap,
                                               ScopeDotAddress startLocation){
        ScopeDotsMap quantizationImage = scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage,0.1);
        List<ScopeDot> flagAddresses= quantizationImage.getScopeDotList();
        //path
        List<CoursePath> coursePathList=new ArrayList<>();
        coursePathList.add(new CoursePath());





        ScopeDot currentScopeDot = ScopeDots.getClosestDot(flagAddresses,startLocation);
        while (flagAddresses.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(flagAddresses, currentScopeDot);
            flagAddresses.remove(currentScopeDot);
            coursePathList.add((ScopeDotAddress) currentScopeDot);
        }
        coursePathList.add(startLocation);
        return coursePathList;

    }
}
