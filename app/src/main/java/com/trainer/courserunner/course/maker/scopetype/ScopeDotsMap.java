package com.trainer.courserunner.course.maker.scopetype;

import com.trainer.courserunner.maps.maploader.MapDAO;
import com.trainer.courserunner.maps.maploader.MapDTO;

import java.util.List;

public class ScopeDotsMap extends ScopeDots {
    ScopeMapInfo scopeMapInfo;

    public ScopeDotsMap(ScopeMapInfo scopeMapInfo) {
        List<MapDTO> scopeMapAddress = MapDAO.getScopeAddress(scopeMapInfo);
        for (MapDTO address : scopeMapAddress) {
            scopeDotList.add(new ScopeDotAddress(scopeMapInfo, address.getX(), address.getY()));
        }
        this.scopeMapInfo = scopeMapInfo;
    }

    public ScopeDotsMap(List<ScopeDot> scopeDotList) {
        this.scopeDotList = scopeDotList;
    }

    public ScopeDotsMap quantizationImageToMap(ScopeDotsImage quanzationInput, ScopeDotsMapQuanzationPolicy quanzationPolicy) {
        return new ScopeDotsMap(quanzationPolicy.quantization(quanzationInput.scopeDotList, this.scopeDotList));
    }

    public ScopeMapInfo getScopeMapInfo() {
        return scopeMapInfo;
    }
}
