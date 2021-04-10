package com.trainer.courserunner.maps;

import androidx.core.util.Consumer;

import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.List;

public interface MapDrawer {
    //draw
    Object drawMarker(ScopeDotAddress address);

    Object drawPolyline(List<ScopeDotAddress> addressList, Consumer<Object> property);

    Object drawPathline(List<ScopeDotAddress> addressList, Consumer<Object> property);

    //default draw
    Object drawPolyline(List<ScopeDotAddress> addressList);

    Object drawPathline(List<ScopeDotAddress> addressList);

    //draw Function
    Object drawRemainPath(List<ScopeDotAddress> addressList);

    Object drawPassedPath(List<ScopeDotAddress> addressList);

    //clear
    void clearDraw(Object drawObject);
}