package com.trainer.courserunner.course.maker.policy.quanzation;

import com.trainer.courserunner.course.maker.scopetype.ScopeDot;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.List;

public interface QuanzationPolicy {
    ScopeDotsMap quantization(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap);
}
