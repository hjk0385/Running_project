package com.trainer.courserunner.course.maker.policy.quanzation;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

public interface QuanzationPolicy {
    ScopeDotsMap quantization(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap);
}
