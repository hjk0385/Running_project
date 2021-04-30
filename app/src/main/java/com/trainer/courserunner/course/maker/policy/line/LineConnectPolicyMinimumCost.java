package com.trainer.courserunner.course.maker.policy.line;

import com.trainer.courserunner.course.maker.scopetype.ScopeDot;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDots;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class LineConnectPolicyMinimumCost implements LineConnectPolicy{
    @Override
    public List<ScopeDotAddress> apply(ScopeDotsMap scopeDotsMap) {
        //불러오기
        List<ScopeDotAddress> course = new ArrayList<>();
        List<ScopeDotAddress> addresses=scopeDotsMap.getScopeDotAddressList();
        //연산
        ScopeDotAddress currentAddress= addresses.get(0);
        addresses.remove(currentAddress);
        course.add(currentAddress);
        //반복
        while (addresses.size() != 0) {
            currentAddress = ScopeDotsMap.getClosestDot(addresses, currentAddress);
            addresses.remove(currentAddress);
            course.add(currentAddress);
        }
        return course;
    }
}
