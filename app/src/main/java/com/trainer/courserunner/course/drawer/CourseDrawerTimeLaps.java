package com.trainer.courserunner.course.drawer;

import android.os.AsyncTask;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDrawerTimeLaps extends CourseDrawerUserRecord {
    Long maxRecordCount;
    Long currentRecordCount;

    public CourseDrawerTimeLaps(MapDrawer mapDrawer, Long userCourseId) {
        super(mapDrawer, userCourseId);
        maxRecordCount=AppDatabaseLoader.getAppDatabase().userCourseRecordDao().getUserLocationRecordCount(userCourseId);
        currentRecordCount= Long.valueOf(0);
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        currentRecordCount=(currentRecordCount+1)%maxRecordCount;

        //불러오기
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        UserCourseRecord[] userLocationRecords = appDatabase.userCourseRecordDao().getUserLocationRecords(userCourseId);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        int i = 0;
        while (i < currentRecordCount) {
            Integer currentDrawingColor = userLocationRecords[i].userCourseRecordColor;
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(currentDrawingColor);
            drawingPathBuilder.setWidth(15);

            if (i > 0) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[i - 1]));
            }
            //컬러 경로 만들기
            int j = i;
            while (j < currentRecordCount && Objects.equals(currentDrawingColor, userLocationRecords[j].userCourseRecordColor)) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[j]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }
}
