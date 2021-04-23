package com.trainer.courserunner.scopetype;

import java.util.List;

public interface ScopeDotsMapQuanzationPolicy {
    List<ScopeDot> quantization(List<ScopeDot> scopeDotListInput, List<ScopeDot> scopeDotListOutput);
}
