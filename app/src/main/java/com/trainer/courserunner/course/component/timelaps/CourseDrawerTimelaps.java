package com.trainer.courserunner.course.component.timelaps;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.course.component.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//미완성
public class CourseDrawerTimelaps extends CourseDrawerUserRecord {
    long lastSize;
    long currentSize;

    public CourseDrawerTimelaps(MapDrawer mapDrawer, Long userCourseId) {
        super(mapDrawer, userCourseId);
        currentSize = 0;
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        //불러오기
        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();
        //임시구현
        lastSize = appDatabase.userCourseRecordDao().getUserLocationRecordCount(userCourseId) - 1;
        currentSize = (currentSize + 1) % lastSize;
        //
        UserCourseRecord[] userLocationRecords = appDatabase.userCourseRecordDao().getUserLocationRecordsLimit(userCourseId, currentSize);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        int i = 0;
        while (i < userLocationRecords.length) {
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
            while (j < userLocationRecords.length && Objects.equals(currentDrawingColor, userLocationRecords[j].userCourseRecordColor)) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[j]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }
}
