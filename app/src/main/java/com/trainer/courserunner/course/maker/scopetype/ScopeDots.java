package com.trainer.courserunner.course.maker.scopetype;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class ScopeDots extends ArrayList<ScopeDot>{
    protected void banRecycle() {
        if (this.size() != 0) {
            throw new IllegalStateException();
        }
    }

    //입력으로 들어온 ScopeDot과 가장 가까운 내부의 점을 반환한다.
    public ScopeDot getClosestDot(ScopeDot scopeDot){
        return this.stream().reduce(null,(ScopeDot prevScopeDot, ScopeDot currentScopeDot)->{
            if(prevScopeDot==null){
                return currentScopeDot;
            }
            if(prevScopeDot.getCost(scopeDot)>currentScopeDot.getCost(scopeDot)){
                return currentScopeDot;
            }
            else{
                return prevScopeDot;
            }
        });
    }
}
