package com.trainer.courserunner.course;

import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.rooms.UserLocationRecord;

//맵에 그려주는 기능 수행
public class CourseDrawer {
    protected MapFlag[] mapFlags;
    AppDatabase appDatabase;
    MapDrawer mapDrawer;
    Object[] courseOverlayMarkers;
    Object courseOverlayPath;
    //사용자의 경로들을 그리기
    Object overlayUserLocationPath;

    public CourseDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
        appDatabase = AppDatabaseLoader.getAppDatabase();
    }

    public void drawCourse(long courseId) {
        mapFlags = appDatabase.courseDao().getCourseMapflags(courseId);
        DrawingPath drawingPath = new DrawingPath(mapFlags);
        courseOverlayPath = mapDrawer.drawCourse(drawingPath);
        courseOverlayMarkers = mapDrawer.drawOverlayMarkers(drawingPath);
    }

    public void clearFlag(long mapflag_id) {
        for (int i = 0; i < mapFlags.length; i++) {
            if (mapFlags[i].mapflag_id == mapflag_id) {
                clearMarker(i);
                break;
            }
        }
    }

    //마커지우기
    private void clearMarker(int markerid) {
        mapDrawer.clearDraw(courseOverlayMarkers[markerid]);
        courseOverlayMarkers[markerid] = null;
    }

    //사용자경로 그리기
    public void drawUserLocationPath(long usercourseId) {
        if (overlayUserLocationPath != null) {
            mapDrawer.clearDraw(overlayUserLocationPath);
            overlayUserLocationPath = null;
        }
        UserLocationRecord[] userLocationRecords = appDatabase.userCourseDao().
                getUserLocationRecords(usercourseId);
        if (userLocationRecords.length <= 2) {
            return;
        }
        DrawingPath drawingPath = new DrawingPath(userLocationRecords);
        overlayUserLocationPath = mapDrawer.drawUserLocationPath(drawingPath);
    }
}
