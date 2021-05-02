package com.trainer.courserunner.course.drawer;

import android.graphics.Color;
import android.util.Log;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDrawerGuideCourse extends CourseDrawer{
    Long courseId;
    public CourseDrawerGuideCourse(MapDrawer mapDrawer, Long courseId) {
        super(mapDrawer);
        this.courseId=courseId;
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
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(Color.BLACK);
            drawingPathBuilder.setWidth(10);
            int j = i;
            while (j < courseFlags.length ) {
                drawingPathBuilder.accept(new DrawingAddress(courseFlags[i]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }
}
