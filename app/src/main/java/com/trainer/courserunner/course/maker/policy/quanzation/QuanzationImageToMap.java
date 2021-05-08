package com.trainer.courserunner.course.maker.policy.quanzation;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.function.BiFunction;

public interface QuanzationImageToMap extends BiFunction<ScopeDotsImage, ScopeDotsMap, ScopeDotsMap> {

}
