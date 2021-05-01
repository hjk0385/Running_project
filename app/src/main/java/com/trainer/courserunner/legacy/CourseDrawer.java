package com.trainer.courserunner.legacy;

import android.graphics.Color;
import android.os.AsyncTask;

import androidx.core.util.Pair;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.rooms.UserLocationRecord;
import com.trainer.courserunner.rooms.UserMapFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//맵에 그려주는 기능 수행
public class CourseDrawer extends AsyncTask<Void, DrawingPath, Void> implements Observer {
    MapDrawer mapDrawer;
    long userCourseId;
    //객체
    AppDatabase appDatabase;
    MapDrawer mapDrawer;
    //정보
    long courseId;
    long userCourseId;
    //오버레이
    Object overlayCoursePath;
    Object[] overlayCourseMarkers;
    Object[] overlayUserLocationPaths;
    public CourseDrawer(MapDrawer mapDrawer, long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.userCourseId = userCourseId;
    }
    public CourseDrawer(MapDrawer mapDrawer, long courseId, long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.appDatabase = AppDatabaseLoader.getAppDatabase();
        this.courseId = courseId;
        this.userCourseId = userCourseId;
        this.overlayUserLocationPaths = null;
        this.overlayCourseMarkers = null;
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public void clearCoursePath() {
        if (overlayCoursePath != null) {
            mapDrawer.clearDraw(overlayCoursePath);
            overlayCoursePath = null;
        }
    }

    //코스그리기
    public void drawCoursePath() {
        clearCoursePath();
        MapFlag[] mapFlags = appDatabase.courseDao().getCourseMapflags(courseId);
        DrawingPath drawingPath = new DrawingPath(mapFlags);
        overlayCoursePath = mapDrawer.drawOverlayPolyline(drawingPath, mapDrawer.getLineColorProperty(Color.BLACK));
    }

    public void clearMarkers() {
        if (overlayCourseMarkers != null) {
            for (Object overlayCourseMarker : overlayCourseMarkers) {
                mapDrawer.clearDraw(overlayCourseMarker);
            }
            overlayCourseMarkers = null;
        }
    }

    //마커그리기
    public void drawMarkers() {
        clearMarkers();
        MapFlag[] mapflags = appDatabase.courseDao().getCourseMapflags(courseId);
        UserMapFlag[] userMapFlags = appDatabase.userCourseDao().getUserMapFlags(userCourseId);
        List<MapFlag> notFinishFlags = new ArrayList<>();
        for (MapFlag mapFlag : mapflags) {
            boolean unfinished = true;
            for (UserMapFlag userMapFlag : userMapFlags) {
                if (mapFlag.mapflag_id == userMapFlag.mapflag_id) {
                    unfinished = false;
                    break;
                }
            }
            if (unfinished) {
                notFinishFlags.add(mapFlag);
            }
        }
        DrawingPath drawingPath = new DrawingPath(notFinishFlags);
        overlayCourseMarkers = mapDrawer.drawOverlayMarkers(drawingPath, mapDrawer.getBaseProperty());
    }

    public void clearUserLocationPath() {
        if (overlayUserLocationPaths != null) {
            for (Object overlayUserLocationPath : overlayUserLocationPaths) {
                mapDrawer.clearDraw(overlayUserLocationPath);
            }
            overlayUserLocationPaths = null;
        }
    }

    //사용자경로 그리기
    public void drawUserLocationPath() {
        clearUserLocationPath();
        UserLocationRecord[] userLocationRecords = appDatabase.userCourseDao().
                getUserLocationRecords(userCourseId);
        if (userLocationRecords.length < 2) {
            return;
        }
        //컬러 분석
        List<Pair<DrawingPath, Integer>> colorDrawing = new ArrayList<>();
        int i = 0;
        while (i < userLocationRecords.length) {
            int drawingColor = userLocationRecords[i].color;
            //컬러 경로 생성
            DrawingPath drawingPath = new DrawingPath();
            if (i > 0) {
                drawingPath.add(new DrawingAddress(userLocationRecords[i - 1]));
            }
            //컬러 경로 만들기
            int j = i;
            while (j < userLocationRecords.length && drawingColor == userLocationRecords[j].color) {
                drawingPath.add(new DrawingAddress(userLocationRecords[j]));
                j++;
            }
            //컬러 경로 저장
            colorDrawing.add(new Pair<>(drawingPath, drawingColor));
            i++;
        }
        //컬러 그리기
        for (Pair<DrawingPath, Integer> colorDraw : colorDrawing) {
            DrawingPath drawingPath = colorDraw.first;
            Integer color = colorDraw.second;
            if (drawingPath.size() < 2) {
                continue;
            }
            mapDrawer.drawOverlayPolyline(drawingPath, mapDrawer.getLineColorProperty(color));
        }
    }


    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}
