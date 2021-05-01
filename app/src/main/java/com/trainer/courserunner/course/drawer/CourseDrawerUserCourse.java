package com.trainer.courserunner.course.drawer;

import android.os.AsyncTask;

import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

//맵에 그려주는 기능 수행
public class CourseDrawerUserCourse extends AsyncTask<Void, Void, List<DrawingPath>> implements Observer {
    MapDrawer mapDrawer;
    long userCourseId;
    Object[] overlayUserLocationPaths;

    public CourseDrawerUserCourse(MapDrawer mapDrawer, long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.userCourseId = userCourseId;
    }

    @Override
    public void update(Observable observable, Object o) {
        execute();
    }

    @Override
    protected List<DrawingPath> doInBackground(Void... voids) {


        return null;
    }

    @Override
    protected void onPostExecute(List<DrawingPath> drawingPaths) {
        super.onPostExecute(drawingPaths);
        clearUserLocationPath();
        //다시그리기
    }
    //drawingpath를 제대로 만든다.

    public List<DrawingPath> drawingPaths() {
        //빌더패턴으로 만들어서 계속해서 그리기


    }

    public void clearUserLocationPath() {
        if (overlayUserLocationPaths != null) {
            for (Object overlayUserLocationPath : overlayUserLocationPaths) {
                mapDrawer.clearDraw(overlayUserLocationPath);
            }
            overlayUserLocationPaths = null;
        }
    }

}
