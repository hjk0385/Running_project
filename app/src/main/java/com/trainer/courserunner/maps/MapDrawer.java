package com.trainer.courserunner.maps;

import androidx.core.util.Consumer;

import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.drawtype.DrawingAddress;

public interface MapDrawer {
    //draw
    Object[] drawOverlayMarkers(DrawingPath drawingPath, Consumer<Object> property);

    Object drawOverlayPolyline(DrawingPath drawingPath, Consumer<Object> property);

    Object drawOverlayPathline(DrawingPath drawingPath, Consumer<Object> property);

    //consumer
    default Consumer<Object> getBaseProperty() {
        return (Object obj) -> {
        };
    }

    Consumer<Object> getLineColorProperty(int color);

    //clear
    void clearDraw(Object drawObject);
}