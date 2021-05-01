package com.trainer.courserunner.course.drawer;

import android.os.AsyncTask;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//맵에 그려주는 기능 수행
public class CourseDrawerUserCourse extends AsyncTask<Void, Void, List<DrawingPath>> implements Observer {
    MapDrawer mapDrawer;
    Long userCourseId;
    List<Object> overlayUserLocationPaths;

    public CourseDrawerUserCourse(MapDrawer mapDrawer, Long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.userCourseId = userCourseId;
        this.overlayUserLocationPaths = new ArrayList<>();
    }

    @Override
    public void update(Observable observable, Object o) {
        execute();
    }

    @Override
    protected List<DrawingPath> doInBackground(Void... voids) {
        //불러오기
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        UserCourseRecord[] userLocationRecords = appDatabase.userCourseRecordDao().getUserLocationRecords(userCourseId);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        Integer i = 0;
        while (i < userLocationRecords.length) {
            Integer currentDrawingColor = userLocationRecords[i].userCourseRecordColor;
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(currentDrawingColor);

            if (i > 0) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[i - 1]));
            }
            //컬러 경로 만들기
            Integer j = i;
            while (j < userLocationRecords.length && currentDrawingColor == userLocationRecords[j].userCourseRecordColor) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[j]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }

    @Override
    protected void onPostExecute(List<DrawingPath> drawingPathList) {
        super.onPostExecute(drawingPathList);
        clearUserLocationPath();
        drawUserLocationPath(drawingPathList);
    }

    public void clearUserLocationPath() {
        if (overlayUserLocationPaths != null) {
            for (Object overlayUserLocationPath : overlayUserLocationPaths) {
                mapDrawer.clearDraw(overlayUserLocationPath);
            }
            overlayUserLocationPaths.clear();
        }
    }

    public void drawUserLocationPath(List<DrawingPath> drawingPaths) {
        for (DrawingPath drawingPath : drawingPaths) {
            overlayUserLocationPaths.add(mapDrawer.drawOverlayPolyline(drawingPath));
        }
    }
}
