package com.trainer.courserunner.course.maker.scopetype;

import com.trainer.courserunner.course.maker.road.RoadAddress;
import com.trainer.courserunner.course.maker.road.RoadAddressDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ScopeDotsMap extends ScopeDots {
    public ScopeDotsMap(ScopeMapInfo scopeMapInfo) {
        List<RoadAddress> scopeMapAddress = RoadAddressDao.getScopeAddress(scopeMapInfo);
        for (RoadAddress address : scopeMapAddress) {
            scopeDotList.add(new ScopeDotAddress(scopeMapInfo, address.getX(), address.getY()));
        }
    }

    public ScopeDotsMap(HashSet<ScopeDotAddress> quantizationDots) {
        scopeDotList = new ArrayList<>(quantizationDots);
    }

    static public ScopeDotAddress getClosestDot(List<ScopeDotAddress> scopeDotList, ScopeDotAddress scopeDot) {
        ScopeDotAddress closestScopeDot = scopeDotList.get(0);
        for (Integer i = 1; i < scopeDotList.size(); i++) {
            if (closestScopeDot.getCost(scopeDot) >
                    scopeDotList.get(i).getCost(scopeDot)) {
                closestScopeDot = scopeDotList.get(i);
            }
        }
        return closestScopeDot;
    }

    public List<ScopeDotAddress> getScopeDotAddressList() {
        List<ScopeDotAddress> scopeDotAddressList = new ArrayList<>();
        for (ScopeDot scopeDot : this.getScopeDotList()) {
            scopeDotAddressList.add((ScopeDotAddress) scopeDot);
        }
        return scopeDotAddressList;
    }
}
