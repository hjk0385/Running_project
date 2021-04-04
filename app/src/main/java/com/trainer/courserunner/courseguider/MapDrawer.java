package com.trainer.courserunner.courseguider;

import androidx.core.util.Consumer;

import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.List;

public interface MapDrawer {
    //draw
    Object drawMarker(DotAddress address, Consumer<Object> property);

    Object drawPolylineOverlay(List<DotAddress> addressList, Consumer<Object> property);

    Object drawPathOverleay(List<DotAddress> addressList, Consumer<Object> property);

    //draw Function
    Object drawPath(List<DotAddress> addressList);

    Object drawRemainPath(List<DotAddress> addressList);

    Object drawPassedPath(List<DotAddress> addressList);

    //clear
    void clearDraw(Object drawObject);
}