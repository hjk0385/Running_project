package com.trainer.courserunner.scopetype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class ScopeDots {
    protected List<ScopeDot> scopeDotList = new ArrayList<>();
    public List<ScopeDot> getScopeDotList() {
        return scopeDotList;
    }

    static public ScopeDot getClosestDot(List<ScopeDot> scopeDotList, ScopeDot scopeDot) {
        ScopeDot closestScopeDot = scopeDotList.get(0);
        for (int i = 1; i < scopeDotList.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDotList.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDotList.get(i);
            }
        }
        return closestScopeDot;
    }
}
