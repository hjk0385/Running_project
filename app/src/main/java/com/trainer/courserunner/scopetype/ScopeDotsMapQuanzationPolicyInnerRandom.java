package com.trainer.courserunner.scopetype;

import java.util.Collections;
import java.util.List;

//랜덤으로 추출
public class ScopeDotsMapQuanzationPolicyInnerRandom extends ScopeDotsMapQuanzationPolicyDefault implements ScopeDotsMapQuanzationPolicy {
    //1 = 전부 , 0 = 없음 , 0.5 = 절반만
    double reminderPercentage;

    public ScopeDotsMapQuanzationPolicyInnerRandom(double reminderPercentage) {
        this.reminderPercentage = reminderPercentage;
    }

    @Override
    public List<ScopeDot> quantization(List<ScopeDot> scopeDotListInput, List<ScopeDot> scopeDotListOutput) {
        List<ScopeDot> quanzationList = super.quantization(scopeDotListInput, scopeDotListOutput);
        int resultSize = (int) (quanzationList.size() * reminderPercentage);
        int deleteSize = quanzationList.size() - resultSize;

        Collections.shuffle(quanzationList);
        for (int i = 0; i < deleteSize; i++) {
            quanzationList.remove(0);
        }
        return quanzationList;
    }
}
