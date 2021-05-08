package com.trainer.courserunner.course.maker.policy.line;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;

import java.util.List;
import java.util.function.BiFunction;

public interface LineConnectPolicy extends BiFunction<List<ScopeDotAddress>, ScopeDotAddress, List<ScopeDotAddress>> {
}
