package com.trainer.courserunner.course.component.drawer;

import android.os.AsyncTask;

import com.trainer.courserunner.course.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class CourseDrawer implements Observer {
    protected MapDrawer mapDrawer;
    protected List<Object> overlayObjs;

    public CourseDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
        this.overlayObjs = new ArrayList<>();
    }

    @Override
    final public void update(Observable observable, Object o) {
        new CourseDrawerAsyncTask().execute();
    }

    abstract protected List<DrawingPath> makeDrawing();

    abstract protected void drawOverlay(List<DrawingPath> drawing);

    final protected void clearOverlay() {
        if (overlayObjs != null) {
            for (Object overlayObj : overlayObjs) {
                mapDrawer.clearDraw(overlayObj);
            }
            overlayObjs.clear();
        }
    }

    public class CourseDrawerAsyncTask extends AsyncTask<Void, Void, List<DrawingPath>> {
        @Override
        protected List<DrawingPath> doInBackground(Void... voids) {
            return makeDrawing();
        }

        @Override
        protected void onPostExecute(List<DrawingPath> drawingPathList) {
            super.onPostExecute(drawingPathList);
            clearOverlay();
            drawOverlay(drawingPathList);
        }

    }
}