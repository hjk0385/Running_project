package com.trainer.courserunner.course;

import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CoursePath;

//맵에 그려주는 기능 수행
public class CourseDrawer {
    MapDrawer mapDrawer;
    protected CoursePath[] courseFlags;
    Object[] courseOverlayMarkers;
    Object courseOverlayPath;

    public CourseDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
    }

    public void drawCourse(long courseId) {
        courseFlags=AppDatabaseLoader.getAppDatabase().coursePathDao().loadCoursePath(courseId);
        DrawingPath drawingPath=new DrawingPath(courseFlags);
        courseOverlayPath=mapDrawer.drawCourse(drawingPath);
        courseOverlayMarkers=mapDrawer.drawOverlayMarkers(drawingPath);
    }

    //마커지우기
    public void clearMarker(int markerid){

    }

    //사용자경로 그리기

}
