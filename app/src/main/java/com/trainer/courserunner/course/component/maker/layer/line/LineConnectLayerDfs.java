package com.trainer.courserunner.course.component.maker.layer.line;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class LineConnectLayerDfs implements LineConnectLayer {
    @Override
    public List<ScopeDotAddress> apply(ScopeDotsMap scopeDots, ScopeDotAddress startAddress) {
        scopeDots = (ScopeDotsMap) scopeDots.clone();

        List<ScopeDotAddress> course = new ArrayList<>();
        ScopeDotAddress currentScopeDotAddress = startAddress;
        course.add(currentScopeDotAddress);

        while (scopeDots.size() != 0) {
            currentScopeDotAddress = (ScopeDotAddress) scopeDots.getClosestDot(currentScopeDotAddress);
            scopeDots.remove(currentScopeDotAddress);
            course.add(currentScopeDotAddress);
        }
        course.add(startAddress);
        return course;
    }
}
