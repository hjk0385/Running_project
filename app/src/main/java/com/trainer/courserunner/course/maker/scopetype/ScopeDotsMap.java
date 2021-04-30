package com.trainer.courserunner.course.maker.scopetype;

import com.trainer.courserunner.course.maker.policy.quanzation.QuanzationPolicy;
import com.trainer.courserunner.map.roadaddress.RoadAddressDao;
import com.trainer.courserunner.map.roadaddress.RoadAddress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ScopeDotsMap extends ScopeDots {
    public ScopeDotsMap(ScopeMapInfo scopeMapInfo) {
        List<RoadAddress> scopeMapAddress = RoadAddressDao.getScopeAddress(scopeMapInfo);
        for (RoadAddress address : scopeMapAddress) {
            scopeDotList.add(new ScopeDotAddress(scopeMapInfo, address.getX(), address.getY()));
        }
    }
    public ScopeDotsMap(HashSet<ScopeDotAddress> quantizationDots){
        scopeDotList=new ArrayList<>(quantizationDots);
    }

    public List<ScopeDotAddress> getScopeDotAddressList() {
        List<ScopeDotAddress> scopeDotAddressList=new ArrayList<>();
        for(ScopeDot scopeDot:this.getScopeDotList()){
            scopeDotAddressList.add((ScopeDotAddress) scopeDot);
        }
        return scopeDotAddressList;
    }

    static public ScopeDotAddress getClosestDot(List<ScopeDotAddress> scopeDotList,ScopeDotAddress scopeDot) {
        ScopeDotAddress closestScopeDot = scopeDotList.get(0);
        for (int i = 1; i < scopeDotList.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDotList.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDotList.get(i);
            }
        }
        return closestScopeDot;
    }
}
