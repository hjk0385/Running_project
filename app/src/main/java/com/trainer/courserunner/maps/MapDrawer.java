package com.trainer.courserunner.maps;

import androidx.core.util.Consumer;

import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.List;

public interface MapDrawer {
    //draw
    Object drawOverlayMarker(ScopeDotAddress address);

    Object drawOverlayPolyline(List<ScopeDotAddress> addressList, Consumer<Object> property);

    Object drawOverlayPathline(List<ScopeDotAddress> addressList, Consumer<Object> property);

    //map draw function
    Object drawRemainPath(List<ScopeDotAddress> addressList);

    Object drawPassedPath(List<ScopeDotAddress> addressList);

    Object drawCourse(List<ScopeDotAddress> addressList);
    //clear
    void clearDraw(Object drawObject);
}