package com.trainer.courserunner.course.component.maker.layer.selection;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseFlag;

public class MarkerSelectionLayerPrecision implements MarkerSelectionLayer {
    double precision;
    public MarkerSelectionLayerPrecision(double precision){
        this.precision=precision;
    }

    @Override
    public Long apply(Long courseId) {
        AppDatabase appDatabase = AppFunctionLoader.getAppDatabase();
        CourseFlag[] courseFlags=appDatabase.courseFlagDao().getCourseFlags(courseId);
        for(CourseFlag courseFlag:courseFlags){
            if(Math.random()<precision){
                courseFlag.markerFlag = true;
            }
            else{
                courseFlag.markerFlag = false;
            }
            appDatabase.courseFlagDao().updateDto(courseFlag);
        }
        return courseId;
    }
}
