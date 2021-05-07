package com.trainer.courserunner.course.drawer;

import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.List;

public abstract class CourseDrawerMarker extends CourseDrawer{
    public CourseDrawerMarker(MapDrawer mapDrawer) {
        super(mapDrawer);
    }

    protected void drawOverlay(List<DrawingPath> drawing) {
        for (DrawingPath drawingPath : drawing) {
            if (drawingPath.size() >= 2) {
                List<Object> markers=mapDrawer.drawOverlayMarkers(drawingPath);
                for(Object marker:markers){
                    overlayObjs.add(marker);
                }
            }
        }
    }
}
