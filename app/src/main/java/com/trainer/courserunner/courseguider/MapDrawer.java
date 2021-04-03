package com.trainer.courserunner.courseguider;

import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.List;

public interface MapDrawer {
    //draw -> drawId
    Object drawMarker(DotAddress address);

    Object drawPolylineOverlay(List<DotAddress> addressList);

    Object drawPathOverleay(List<DotAddress> addressList);

    //clear
    void clearDraw(Object drawObject);
}