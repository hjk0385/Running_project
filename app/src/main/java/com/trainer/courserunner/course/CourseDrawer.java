package com.trainer.courserunner.course;

import android.graphics.Color;

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
    Object overlayUserLocationPath;

    public CourseDrawer(MapDrawer mapDrawer, long courseId, long userCourseId) {
        this.mapDrawer = mapDrawer;
        this.appDatabase = AppDatabaseLoader.getAppDatabase();
        this.courseId = courseId;
        this.userCourseId = userCourseId;
        this.overlayUserLocationPath = null;
        this.overlayCourseMarkers = null;
    }

    public void mapStart() {
        drawCoursePath();
        drawMarkers();
    }

    public void mapRefresh() {
        drawMarkers();
        drawUserLocationPath();
    }

    private void drawCoursePath() {
        if (overlayCoursePath != null) {
            mapDrawer.clearDraw(overlayCoursePath);
            overlayCoursePath = null;
        }
        MapFlag[] mapFlags = appDatabase.courseDao().getCourseMapflags(courseId);
        DrawingPath drawingPath = new DrawingPath(mapFlags);
        overlayCoursePath = mapDrawer.drawOverlayPolyline(drawingPath,mapDrawer.getLineColorProperty(Color.BLACK));
    }

    private void drawMarkers() {
        if (overlayCourseMarkers != null) {
            for (Object overlayCourseMarker : overlayCourseMarkers) {
                mapDrawer.clearDraw(overlayCourseMarker);
            }
            overlayCourseMarkers = null;
        }
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
        overlayCourseMarkers = mapDrawer.drawOverlayMarkers(drawingPath,mapDrawer.getBaseProperty());
    }

    //사용자경로 그리기
    private void drawUserLocationPath() {
        if (overlayUserLocationPath != null) {
            mapDrawer.clearDraw(overlayUserLocationPath);
            overlayUserLocationPath = null;
        }
        UserLocationRecord[] userLocationRecords = appDatabase.userCourseDao().
                getUserLocationRecords(userCourseId);
        if (userLocationRecords.length <= 2) {
            return;
        }

        int currentColor=userLocationRecords[0].color;
        DrawingPath currentDrawing = new DrawingPath(userLocationRecords);
        for(int i=0;i<userLocationRecords.length;i++){
            double latitude=userLocationRecords[i].latitude;
            double longitude=userLocationRecords[i].longitude;
            int nextColor=userLocationRecords[i].color;
            currentDrawing.add(new DrawingAddress(latitude,longitude));
            if(currentColor!=nextColor){

            }
        }


        List<DrawingPath> drawingPaths=new ArrayList<>();
        overlayUserLocationPath = mapDrawer.drawOverlayPathline(drawingPath,mapDrawer.getLineColorProperty(Color.RED));
    }
}
