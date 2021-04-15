package com.trainer.courserunner.maps;

import androidx.core.util.Consumer;

import com.trainer.courserunner.drawtype.DrawingAddress;
import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.List;

public interface MapDrawer {
    //draw
    Object drawOverlayMarker(ScopeDotAddress address);

    Object drawOverlayPolyline(DrawingPath drawingPath, Consumer<Object> property);

    Object drawOverlayPathline(DrawingPath drawingPath, Consumer<Object> property);

    //map draw function
    Object drawRemainPath(DrawingPath drawingPath);

    Object drawPassedPath(DrawingPath drawingPath);

    Object drawCourse(DrawingPath addressList);
    //clear
    void clearDraw(Object drawObject);
}