package com.trainer.courserunner.course;

import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.List;

public class CourseRegister {
    static int regist(List<ScopeDotAddress> course, AppDatabase appDatabase){
        int maxCourseId=appDatabase.courseDao().loadMaxCourseId();
        int course_id=maxCourseId+1;
        for(int i=0;i<course.size();i++){
            CoursePath coursePath = new CoursePath();
            coursePath.course_id=course_id;
            coursePath.coordinate_number=i;
            coursePath.latitude=course.get(i).getLatitude();
            coursePath.longtitude=course.get(i).getLongitude();
        }
        return course_id;
    }
}
