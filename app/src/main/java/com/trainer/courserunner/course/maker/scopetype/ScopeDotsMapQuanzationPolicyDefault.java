package com.trainer.courserunner.course.maker.scopetype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ScopeDotsMapQuanzationPolicyDefault implements ScopeDotsMapQuanzationPolicy {
    @Override
    public List<ScopeDot> quantization(List<ScopeDot> scopeDotListInput, List<ScopeDot> scopeDotListOutput) {
        HashSet<ScopeDot> quantizationDots = new HashSet<>();
        for (ScopeDot scopeDot : scopeDotListInput) {
            quantizationDots.add(ScopeDotsMap.getClosestDot(scopeDotListOutput, scopeDot));
        }
        return new ArrayList<>(quantizationDots);
    }
}
