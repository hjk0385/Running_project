package com.trainer.courserunner.course.drawer;

import android.graphics.Color;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;

import java.util.ArrayList;
import java.util.List;

public class CourseDrawerGuideLine extends CourseDrawerPolyline{
    Long courseId;

    public CourseDrawerGuideLine(MapDrawer mapDrawer, Long courseId) {
        super(mapDrawer);
        this.courseId = courseId;
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        //불러오기
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseFlags(courseId);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        int i = 0;
        while (i < courseFlags.length) {
            Integer currentDrawingColor = Color.BLUE;
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(currentDrawingColor);
            drawingPathBuilder.setWidth(10);

            if (i > 0) {
                drawingPathBuilder.accept(new DrawingAddress(courseFlags[i - 1]));
            }
            //컬러 경로 만들기
            int j = i;
            while (j < courseFlags.length) {
                drawingPathBuilder.accept(new DrawingAddress(courseFlags[j]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }

}
