package com.trainer.courserunner.course;

import android.graphics.Color;

import androidx.core.util.Pair;

import com.trainer.courserunner.drawtype.DrawingAddress;
import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.rooms.UserLocationRecord;
import com.trainer.courserunner.rooms.UserMapFlag;

import java.util.ArrayList;
import java.util.List;

//맵에 그려주는 기능 수행
public class CourseDrawer {
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

    public CourseDrawer(MapDrawer mapDrawer, long courseId, long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.appDatabase = AppDatabaseLoader.getAppDatabase();
        this.courseId = courseId;
        this.userCourseId = userCourseId;
        this.overlayUserLocationPaths = null;
        this.overlayCourseMarkers = null;
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
}
