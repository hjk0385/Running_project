package com.trainer.courserunner.course;

import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseInfo;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.List;

public class CourseRegister {
    static int regist(List<ScopeDotAddress> course, AppDatabase appDatabase,int startX,int startY,int endX,int endY){
        int maxCourseId=appDatabase.courseInfoDao().loadMaxCourseId();
        final int course_id=maxCourseId+1;
        //코스 정보 등록
        CourseInfo courseInfo=new CourseInfo();
        courseInfo.course_id=course_id;
        courseInfo.start_latitude=startY;
        courseInfo.start_longtitude=startX;
        courseInfo.end_latitude=endY;
        courseInfo.end_longtitude=endX;
        appDatabase.courseInfoDao().insertCourseInfo(courseInfo);

        //코스 등록
        for(int i=0;i<course.size();i++){
            CoursePath coursePath = new CoursePath();
            coursePath.course_id=course_id;
            coursePath.coordinate_number=i;
            coursePath.latitude=course.get(i).getLatitude();
            coursePath.longtitude=course.get(i).getLongitude();
            appDatabase.courseDao().insertCourse(coursePath);
        }
        return course_id;
    }
}
