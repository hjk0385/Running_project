package com.trainer.courserunner.maps;

import androidx.core.util.Consumer;

import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.List;

public interface MapDrawer {
    //draw
    Object drawMarker(ScopeDotAddress address, Consumer<Object> property);

    Object drawPolyline(List<ScopeDotAddress> addressList, Consumer<Object> property);

    Object drawPath(List<ScopeDotAddress> addressList, Consumer<Object> property);

    //draw Function
    Object drawPath(List<ScopeDotAddress> addressList);

    Object drawRemainPath(List<ScopeDotAddress> addressList);

    Object drawPassedPath(List<ScopeDotAddress> addressList);

    //clear
    void clearDraw(Object drawObject);
}