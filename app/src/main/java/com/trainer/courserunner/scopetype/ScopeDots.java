package com.trainer.courserunner.scopetype;

import java.util.ArrayList;
import java.util.List;

public class ScopeDots {
    protected List<ScopeDot> dots = new ArrayList<>();

    static public ScopeDot getClosestDot(List<ScopeDot> scopeDots, ScopeDot scopeDot) {
        ScopeDot closestScopeDot = scopeDots.get(0);
        for (int i = 1; i < scopeDots.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDots.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDots.get(i);
            }
        }
        return closestScopeDot;
    }

    public ScopeDot getClosestDot(ScopeDot scopeDot) {
        return ScopeDots.getClosestDot(this.dots, scopeDot);
    }
}
