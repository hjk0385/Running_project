package com.trainer.courserunner.courseguider;

import java.util.List;
import com.trainer.courserunner.coursesuggest.DotAddress;

public interface MapDrawer {
    //draw -> drawId
    Object drawMarker(DotAddress address);
    Object drawPolylineOverlay(List<DotAddress> addressList);
    Object drawPathOverleay(List<DotAddress> addressList);
    //clear
    void clearDraw(Object drawObject);
}