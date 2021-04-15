package com.trainer.courserunner.maps;

import androidx.core.util.Consumer;

import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.drawtype.DrawingAddress;

public interface MapDrawer {
    //draw
    Object drawOverlayMarker(DrawingAddress address);

    Object drawOverlayPolyline(DrawingPath drawingPath, Consumer<Object> property);

    Object drawOverlayPathline(DrawingPath drawingPath, Consumer<Object> property);

    //코스
    Object drawCourse(DrawingPath drawingPath);

    //clear
    void clearDraw(Object drawObject);
}