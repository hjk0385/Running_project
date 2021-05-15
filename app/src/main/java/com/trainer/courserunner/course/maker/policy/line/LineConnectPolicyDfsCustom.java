package com.trainer.courserunner.course.maker.policy.line;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class LineConnectPolicyDfsCustom implements LineConnectPolicy{
    double precision;

    public LineConnectPolicyDfsCustom(double precision){
        this.precision=precision;
    }

    @Override
    public List<ScopeDotAddress> apply(List<ScopeDotAddress> scopeDotAddressList, ScopeDotAddress scopeDotAddress) {
        List<ScopeDotAddress> course = new ArrayList<>();
        course.add(scopeDotAddress);

        ScopeDotAddress currentAddress = scopeDotAddress;
        while (scopeDotAddressList.size() > 0) {
            currentAddress = ScopeDotsMap.getClosestDot(scopeDotAddressList, currentAddress);
            scopeDotAddressList.remove(currentAddress);
            course.add(currentAddress);

            List<ScopeDotAddress> deleteAddresses=new ArrayList<>();
            for(ScopeDotAddress innerLoopAddress:scopeDotAddressList){
                double cost=innerLoopAddress.getCost(currentAddress);
                if(cost<precision){
                    //주위에 있는 점들을 제거
                    deleteAddresses.add(innerLoopAddress);
                }
            }
            scopeDotAddressList.removeAll(deleteAddresses);
        }
        course.add(scopeDotAddress);
        return course;
    }
}
