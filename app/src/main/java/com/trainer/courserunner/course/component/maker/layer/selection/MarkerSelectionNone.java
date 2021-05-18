package com.trainer.courserunner.course.component.maker.layer.selection;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;

//아무 마커도 생성하고 싶지 않은 경우
public class MarkerSelectionNone implements MarkerSelectionLayer{
    @Override
    public Long apply(Long courseId) {
        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseFlags(courseId);
        for (CourseFlag courseFlag : courseFlags) {
            courseFlag.markerFlag = false;
            appDatabase.courseFlagDao().updateDto(courseFlag);
        }
        return courseId;
    }
}
