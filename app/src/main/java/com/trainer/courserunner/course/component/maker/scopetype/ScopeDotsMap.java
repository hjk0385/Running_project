package com.trainer.courserunner.course.component.maker.scopetype;

import com.trainer.courserunner.Application.road.RoadAddress;
import com.trainer.courserunner.Application.road.RoadAddressDao;

import java.util.List;
import java.util.stream.Collectors;

public class ScopeDotsMap extends ScopeDots{
    public ScopeDotsMap(ScopeMapInfo scopeMapInfo){
        super();
        List<ScopeDot> scopeDotList = RoadAddressDao.getScopeAddress(scopeMapInfo).parallelStream()
                .map((RoadAddress roadAddress)->new ScopeDotAddress(scopeMapInfo,roadAddress.getX(),roadAddress.getY()))
                .collect(Collectors.toList());
        this.addAll(scopeDotList);
    }

    public ScopeDotsMap(List<ScopeDotAddress> scopeDotAddressList){
        super();
        this.addAll(scopeDotAddressList);
    }
}
