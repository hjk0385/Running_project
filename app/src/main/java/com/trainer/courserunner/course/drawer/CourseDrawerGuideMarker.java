package com.trainer.courserunner.course.drawer;

import android.graphics.Color;
import android.util.Log;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.UserCourseFlagDerived;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseDrawerGuideMarker extends CourseDrawerMarker{
    Long courseId;
    Long userCourseId;
    public CourseDrawerGuideMarker(MapDrawer mapDrawer, Long courseId, Long userCourseId) {
        super(mapDrawer);
        this.courseId=courseId;
        this.userCourseId=userCourseId;
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        CourseFlag[] courseFlags = UserCourseFlagDerived.getUnvistedUserCourseFlags(courseId,userCourseId);
        Log.v("CourseFlags", String.valueOf(courseFlags.length));
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
