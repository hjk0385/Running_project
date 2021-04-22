package com.trainer.courserunner.course;

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
        reDrawMarkers();
    }

    public void mapRefresh() {
        reDrawMarkers();
        drawUserLocationPath();
    }

    private void drawCoursePath() {
        if (overlayCoursePath != null) {
            mapDrawer.clearDraw(overlayCoursePath);
            overlayCoursePath = null;
        }
        MapFlag[] mapFlags = appDatabase.courseDao().getCourseMapflags(courseId);
        DrawingPath drawingPath = new DrawingPath(mapFlags);
        overlayCoursePath = mapDrawer.drawCourse(drawingPath);
    }

    private void reDrawMarkers() {
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
        overlayCourseMarkers = mapDrawer.drawOverlayMarkers(drawingPath);
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
        DrawingPath drawingPath = new DrawingPath(userLocationRecords);
        overlayUserLocationPath = mapDrawer.drawUserLocationPath(drawingPath);
    }
}
