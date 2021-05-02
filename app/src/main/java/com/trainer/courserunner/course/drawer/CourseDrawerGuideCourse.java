package com.trainer.courserunner.course.drawer;

import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.ArrayList;
import java.util.List;

public class CourseDrawerGuideCourse extends CourseDrawer{
    
    public CourseDrawerGuideCourse(MapDrawer mapDrawer, Long courseId) {
        super(mapDrawer);

    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        return null;
    }

    @Override
    protected void drawOverlay(List<DrawingPath> drawing) {

    }

    @Override
    protected void clearOverlay() {

    }
}
