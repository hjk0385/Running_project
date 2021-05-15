package com.trainer.courserunner.course.maker.scopetype;

import com.trainer.courserunner.course.maker.road.RoadAddress;
import com.trainer.courserunner.course.maker.road.RoadAddressDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ScopeDotsMap extends ScopeDots {
    public void loadMap(ScopeMapInfo scopeMapInfo){
        banRecycle();
        List<ScopeDot> scopeDotList = RoadAddressDao.getScopeAddress(scopeMapInfo).stream()
                .map((RoadAddress roadAddress)->new ScopeDotAddress(scopeMapInfo,roadAddress.getX(),roadAddress.getY()))
                .collect(Collectors.toList());
        this.addAll(scopeDotList);
    }
}
