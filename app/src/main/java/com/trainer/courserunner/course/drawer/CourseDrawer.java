package com.trainer.courserunner.course.drawer;

import android.os.AsyncTask;

import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class CourseDrawer extends AsyncTask<Void, Void, List<DrawingPath>> implements Observer {
    protected MapDrawer mapDrawer;
    protected List<Object> overlayObjs;

    public CourseDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
        this.overlayObjs = new ArrayList<>();
    }

    @Override
    final protected List<DrawingPath> doInBackground(Void... voids) {
        return makeDrawing();
    }

    @Override
    final public void update(Observable observable, Object o) {
        execute();
    }

    @Override
    final protected void onPostExecute(List<DrawingPath> drawingPathList) {
        super.onPostExecute(drawingPathList);
        clearOverlay();
        drawOverlay(drawingPathList);
    }

    abstract protected List<DrawingPath> makeDrawing();

    protected void drawOverlay(List<DrawingPath> drawing) {
        for (DrawingPath drawingPath : drawing) {
            if (drawingPath.size() >= 2) {
                overlayObjs.add(mapDrawer.drawOverlayPolyline(drawingPath));
            }
        }
    }

    final protected void clearOverlay() {
        if (overlayObjs != null) {
            for (Object overlayObj : overlayObjs) {
                mapDrawer.clearDraw(overlayObj);
            }
            overlayObjs.clear();
        }
    }
}
