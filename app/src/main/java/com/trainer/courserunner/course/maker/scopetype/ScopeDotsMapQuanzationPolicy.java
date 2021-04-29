package com.trainer.courserunner.course.maker.scopetype;

import java.util.List;

public interface ScopeDotsMapQuanzationPolicy {
    List<ScopeDot> quantization(List<ScopeDot> scopeDotListInput, List<ScopeDot> scopeDotListOutput);
}
