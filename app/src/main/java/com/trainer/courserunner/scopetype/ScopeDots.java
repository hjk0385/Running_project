package com.trainer.courserunner.scopetype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class ScopeDots {
    protected List<ScopeDot> scopeDotList = new ArrayList<>();
    public ScopeDot getClosestDot(ScopeDot scopeDot){
        ScopeDot closestScopeDot = scopeDotList.get(0);
        for (int i = 1; i < scopeDotList.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDotList.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDotList.get(i);
            }
        }
        return closestScopeDot;
    }
    static public ScopeDot getClosestDot(List<ScopeDot> scopeDotList, ScopeDot scopeDot){
        ScopeDot closestScopeDot = scopeDotList.get(0);
        for (int i = 1; i < scopeDotList.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDotList.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDotList.get(i);
            }
        }
        return closestScopeDot;
    }
    public List<ScopeDot> quantization(ScopeDots scopeDots){
        //입력으로 받은 ScopeDots를 현재 ScopeDots로 변환한다.
        HashSet<ScopeDot> quantizationDots = new HashSet<>();
        for (ScopeDot scopeDot : scopeDots.scopeDotList) {
            quantizationDots.add(this.getClosestDot(scopeDot));
        }
        return new ArrayList<>(quantizationDots);
    }
}
