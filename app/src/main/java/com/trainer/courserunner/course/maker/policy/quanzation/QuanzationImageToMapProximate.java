package com.trainer.courserunner.course.maker.policy.quanzation;

import com.trainer.courserunner.course.maker.scopetype.ScopeDot;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.HashSet;

public class QuanzationImageToMapProximate implements QuanzationImageToMap{
    @Override
    public ScopeDotsMap apply(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap) {
        HashSet<ScopeDotAddress> quantizationDots = new HashSet<>();
        for (ScopeDot scopeDot : scopeDotsImage.getScopeDotList()) {
            quantizationDots.add((ScopeDotAddress) ScopeDotsMap.getClosestDot(scopeDotsMap.getScopeDotList(), scopeDot));
        }
        return new ScopeDotsMap(quantizationDots);
    }
}
