package com.trainer.courserunner.Application.mapdb.newimpl.data;

import com.trainer.courserunner.Application.mapdb.newimpl.data.AddressVO;


import java.util.List;

public class RangeMap {
    List<AddressVO> mapVoList;

    public RangeMap(List<AddressVO> mapVoList) {
        this.mapVoList = mapVoList;
    }

    public List<AddressVO> getMapVoList() {
        return mapVoList;
    }
}
