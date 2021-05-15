package com.trainer.courserunner.course.component.maker.layer.regist;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.Course;
import com.trainer.courserunner.rooms.CourseFlag;

import java.util.List;

public class CourseRegistLayerAll implements CourseRegistLayer{
    @Override
    public Long apply(List<ScopeDotAddress> course) {
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        Long courseId = appDatabase.courseDao().insertDto(new Course());
        for (int i = 0; i < course.size(); i++) {
            CourseFlag courseFlag = new CourseFlag();
            courseFlag.courseId = courseId;
            courseFlag.courseFlagId = (long) i;
            courseFlag.courseFlagLatitude = course.get(i).getLatitude();
            courseFlag.courseFlagLongitude = course.get(i).getLongitude();
            if(Math.random()<=0.75){
                courseFlag.markerFlag = true;
            }
            else{
                courseFlag.markerFlag = false;
            }
            appDatabase.courseFlagDao().insertDto(courseFlag);
        }
        return courseId;
    }
}
