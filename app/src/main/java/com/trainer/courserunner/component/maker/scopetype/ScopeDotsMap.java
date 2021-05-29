package com.trainer.courserunner.component.maker.scopetype;

import com.trainer.courserunner.Application.geo.RoadAddressDto;
import com.trainer.courserunner.Application.geo.RoadAddressDao;

import java.util.List;
import java.util.stream.Collectors;

public class ScopeDotsMap extends ScopeDots {
    public ScopeDotsMap(ScopeMapInfo scopeMapInfo) {
        super();
        List<ScopeDot> scopeDotList = RoadAddressDao.getScopeAddress(scopeMapInfo).parallelStream()
                .map((RoadAddressDto roadAddressDto) -> new ScopeDotAddress(scopeMapInfo, roadAddressDto.getX(), roadAddressDto.getY()))
                .collect(Collectors.toList());
        this.addAll(scopeDotList);
    }

    public ScopeDotsMap(List<ScopeDotAddress> scopeDotAddressList) {
        super();
        this.addAll(scopeDotAddressList);
    }
}
