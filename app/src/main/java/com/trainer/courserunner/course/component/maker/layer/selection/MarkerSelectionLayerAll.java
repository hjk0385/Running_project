package com.trainer.courserunner.course.component.maker.layer.selection;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;

public class MarkerSelectionLayerAll implements MarkerSelectionLayer {
    @Override
    public Long apply(Long courseId) {
        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseFlags(courseId);
        for (CourseFlag courseFlag : courseFlags) {
            courseFlag.markerFlag = true;
            appDatabase.courseFlagDao().updateDto(courseFlag);
        }
        return courseId;
    }
}
