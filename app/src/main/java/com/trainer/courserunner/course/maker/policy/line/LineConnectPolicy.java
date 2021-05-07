package com.trainer.courserunner.course.maker.policy.line;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface LineConnectPolicy extends BiFunction<List<ScopeDotAddress>, ScopeDotAddress,List<ScopeDotAddress>> {
}
