package com.trainer.courserunner.course;

import android.content.Context;

import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseInstance;
import com.trainer.courserunner.rooms.CourseInfo;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

public class CourseMaker {
    AppDatabaseInstance appDatabaseInstance;
    public CourseMaker(AppDatabaseInstance appDatabaseInstance){
        this.appDatabaseInstance=appDatabaseInstance;
    }

    public long makeCourse(ScopeDotsImage scopeDotsImage,
                                  ScopeDotsMap scopeDotsMap,
                                  ScopeDotAddress startLocation){
        AppDatabase appDatabase=appDatabaseInstance.getAppDatabase();
        //코스 정보 저장
        CourseInfo courseInfo =new CourseInfo();
        courseInfo.start_latitude=scopeDotsMap.getScopeMapInfo().getStartLatitude();
        courseInfo.start_longtitude=scopeDotsMap.getScopeMapInfo().getStartLongtitude();
        courseInfo.end_latitude=scopeDotsMap.getScopeMapInfo().getEndLatitude();
        courseInfo.end_longtitude=scopeDotsMap.getScopeMapInfo().getEndLongtitude();
        long courseNumber=appDatabase.courseInfoDao().insertCourseInfo(courseInfo);
        //코스 경로 저장
        

        return courseNumber;
    }


}
