package com.trainer.courserunner.course.component.drawer;

import com.trainer.courserunner.course.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.List;

public abstract class CourseDrawerPathline extends CourseDrawer {
    public CourseDrawerPathline(MapDrawer mapDrawer) {
        super(mapDrawer);
    }

    protected void drawOverlay(List<DrawingPath> drawing) {
        for (DrawingPath drawingPath : drawing) {
            if (drawingPath.size() >= 2) {
                overlayObjs.add(mapDrawer.drawOverlayPathline(drawingPath));
            }
        }
    }
}
