package com.trainer.courserunner.course.maker.policy.quanzation;

import com.trainer.courserunner.course.maker.scopetype.ScopeDot;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

//랜덤으로 추출
public class QuanzationPolicyRandom extends QuanzationPolicyDefault implements QuanzationPolicy {
    //1 = 전부 , 0 = 없음 , 0.5 = 절반만
    double reminderPercentage;

    public QuanzationPolicyRandom(double reminderPercentage) {
        this.reminderPercentage = reminderPercentage;
    }
    @Override
    public ScopeDotsMap quantization(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap){
        ScopeDotsMap courseMap=super.quantization(scopeDotsImage,scopeDotsMap);
        //변경
        List<ScopeDot> quanzationList=courseMap.getScopeDotList();
        int resultSize = (int) (quanzationList.size() * reminderPercentage);
        int deleteSize = quanzationList.size() - resultSize;
        Collections.shuffle(quanzationList);
        if (deleteSize > 0) {
            quanzationList.subList(0, deleteSize).clear();
        }
        //반환
        return courseMap;
    }
}
