package com.trainer.courserunner.course;

import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.UserLocationPath;

//맵에 그려주는 기능 수행
public class CourseDrawer {
    protected CourseFlag[] courseFlags;
    MapDrawer mapDrawer;
    Object[] courseOverlayMarkers;
    Object courseOverlayPath;

    public CourseDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
        courseFlags=null;
        courseOverlayMarkers=null;
        courseOverlayPath=null;
        overlayUserLocationPath=null;
    }

    public void drawCourse(long courseId) {
        courseFlags = AppDatabaseLoader.getAppDatabase().coursePathDao().loadCourseFlag(courseId);
        DrawingPath drawingPath = new DrawingPath(courseFlags);
        courseOverlayPath = mapDrawer.drawCourse(drawingPath);
        courseOverlayMarkers = mapDrawer.drawOverlayMarkers(drawingPath);
    }

    //마커지우기
    public void clearMarker(int markerid) {
        mapDrawer.clearDraw(courseOverlayMarkers[markerid]);
        courseOverlayMarkers[markerid] = null;
    }

    Object overlayUserLocationPath;
    //사용자경로 그리기
    public void drawUserLocationPath(long usercourseId){
        if(overlayUserLocationPath!=null){
            mapDrawer.clearDraw(overlayUserLocationPath);
            overlayUserLocationPath=null;
        }
        UserLocationPath[] userLocationPaths = AppDatabaseLoader.getAppDatabase().
                userLocationPathDao().queryUserLocationPath(usercourseId);
        if(userLocationPaths.length<=2){
            return;
        }
        DrawingPath drawingPath=new DrawingPath(userLocationPaths);
        overlayUserLocationPath=mapDrawer.drawUserLocationPath(drawingPath);
    }
}
