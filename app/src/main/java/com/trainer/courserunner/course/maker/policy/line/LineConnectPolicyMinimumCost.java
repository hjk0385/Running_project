package com.trainer.courserunner.course.maker.policy.line;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class LineConnectPolicyMinimumCost implements LineConnectPolicy {
    @Override
    public List<ScopeDotAddress> apply(List<ScopeDotAddress> scopeDotAddressList, ScopeDotAddress currentScopeDotAddress) {
        //불러오기
        List<ScopeDotAddress> course = new ArrayList<>();
        //연산
        ScopeDotAddress currentAddress = currentScopeDotAddress;
        course.add(currentAddress);
        //반복
        while (scopeDotAddressList.size() != 0) {
            currentAddress = ScopeDotsMap.getClosestDot(scopeDotAddressList, currentAddress);
            scopeDotAddressList.remove(currentAddress);
            course.add(currentAddress);
        }
        course.add(currentScopeDotAddress);
        return course;
    }
}
