package com.trainer.courserunner.course.maker.policy.quanzation;

import com.trainer.courserunner.course.maker.scopetype.ScopeDot;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDots;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QuanzationImageToMapPrecision extends QuanzationImageToMapProximate{
    double precision;
    public QuanzationImageToMapPrecision(double precision){
        this.precision=precision;
    }

    @Override
    public ScopeDotsMap apply(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap) {
        ScopeDotsMap result1=super.apply(scopeDotsImage,scopeDotsMap);
        HashSet<ScopeDotAddress> quantizationDots = new HashSet<>();
        for(double currentX=0.0;currentX<1.0;currentX+=0.1){
            for(double currentY=0.0;currentY<1.0;currentY+=0.1){
                quantizationDots.add((ScopeDotAddress)
                        ScopeDots.getClosestDot(result1.getScopeDotList(),new ScopeDot(currentX,currentY)));
            }
        }
        return new ScopeDotsMap(quantizationDots);
    }

}
