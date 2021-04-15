package com.trainer.courserunner.course;

import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CourseInfo;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class CourseMaker {
    private List<ScopeDotAddress> makeConnectedPath(List<ScopeDot> flagAddresses,ScopeDotAddress startLocation){
        List<ScopeDotAddress> course=new ArrayList<>();
        course.add((ScopeDotAddress) startLocation);
        ScopeDot currentScopeDot = ScopeDots.getClosestDot(flagAddresses, startLocation);
        while (flagAddresses.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(flagAddresses, currentScopeDot);
            flagAddresses.remove(currentScopeDot);
            course.add((ScopeDotAddress) currentScopeDot);
        }
        course.add(startLocation);
        return course;
    }

    public long makeCourse(ScopeDotsImage scopeDotsImage,
                                  ScopeDotsMap scopeDotsMap,
                                  ScopeDotAddress startLocation){
        AppDatabase appDatabase= AppDatabaseLoader.getAppDatabase();
        //코스 정보 저장
        CourseInfo courseInfo =new CourseInfo();
        courseInfo.start_latitude=scopeDotsMap.getScopeMapInfo().getStartLatitude();
        courseInfo.start_longtitude=scopeDotsMap.getScopeMapInfo().getStartLongtitude();
        courseInfo.end_latitude=scopeDotsMap.getScopeMapInfo().getEndLatitude();
        courseInfo.end_longtitude=scopeDotsMap.getScopeMapInfo().getEndLongtitude();
        long course_id=appDatabase.courseInfoDao().insertCourseInfo(courseInfo);
        //코스 생성 (정밀도 0.1)
        ScopeDotsMap quantizationImage = scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage,0.1);
        List<ScopeDotAddress> course=makeConnectedPath(quantizationImage.getScopeDotList(),startLocation);
        //코스 경로 저장(나중에 개선)
        List<CoursePath> coursePathList=new ArrayList<>();
        for(int i=0;i<course.size();i++){
            CoursePath coursePath=new CoursePath();
            coursePath.course_id=course_id;
            coursePath.coursepath_id=i;
            coursePath.longtitude=course.get(i).getLongitude();
            coursePath.latitude=course.get(i).getLatitude();
            appDatabase.coursePathDao().insertCoursePath(coursePath);
        }
        return course_id;
    }
}
