package com.trainer.courserunner.course.maker.scopetype;

import java.util.ArrayList;
import java.util.List;

public abstract class ScopeDots {
    protected List<ScopeDot> scopeDotList = new ArrayList<>();

    static public ScopeDot getClosestDot(List<ScopeDot> scopeDotList, ScopeDot scopeDot) {
        ScopeDot closestScopeDot = scopeDotList.get(0);
        for (Integer i = 1; i < scopeDotList.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDotList.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDotList.get(i);
            }
        }
        return closestScopeDot;
    }

    public List<ScopeDot> getScopeDotList() {
        return scopeDotList;
    }
}
