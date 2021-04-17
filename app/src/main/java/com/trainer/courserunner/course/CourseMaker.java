package com.trainer.courserunner.course;

import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.AppDatabaseLoader;
import com.trainer.courserunner.rooms.CourseFlag;
import com.trainer.courserunner.rooms.CourseInfo;
import com.trainer.courserunner.rooms.MapFlag;
import com.trainer.courserunner.scopetype.ScopeDot;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDots;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CourseMaker {
    private List<ScopeDotAddress> makeConnectedPath(List<ScopeDot> flagAddresses, ScopeDotAddress startLocation) {
        List<ScopeDotAddress> course = new ArrayList<>();
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
                           ScopeDotAddress startLocation) {
        ScopeDotsMap quantizationImage = scopeDotsMap.quantizationToScopeDotsMap(scopeDotsImage, 0.1);
        List<ScopeDotAddress> mapFlags = makeConnectedPath(quantizationImage.getScopeDotList(), startLocation);
        //
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        //맵저장
        long[] mapFlagIds= this.registMapFlags(appDatabase,mapFlags);
        //코스 등록
        long courseId=this.registCourseInfo(appDatabase);
        this.registCourseFlags(appDatabase,mapFlagIds,courseId);
        return courseId;
    }

    private long registCourseInfo(AppDatabase appDatabase){
        return appDatabase.courseInfoDao().insertCourseInfo(new CourseInfo());
    }

    private long[] registMapFlags(AppDatabase appDatabase, List<ScopeDotAddress> course){
        long[] mapFlagIds=new long[course.size()];
        for (int i = 0; i < course.size(); i++) {
            MapFlag mapFlag=new MapFlag();
            mapFlag.latitude=course.get(i).getLatitude();
            mapFlag.longitude=course.get(i).getLongitude();
            mapFlagIds[i] = appDatabase.mapFlagDao().insertMapFlag(mapFlag);
        }
        return mapFlagIds;
    }

    private void registCourseFlags(AppDatabase appDatabase,long[] mapFlagIds,long courseId){
        for(int i=0;i<mapFlagIds.length;i++){
            CourseFlag courseFlag=new CourseFlag();
            courseFlag.course_id=courseId;
            courseFlag.mapflag_id=mapFlagIds[i];
            courseFlag.courseflag_order=i;
            appDatabase.courseFlagDao().insertCourseFlag(courseFlag);
        }
    }
}
