package com.trainer.courserunner.course;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseInfo;
import com.trainer.courserunner.rooms.CoursePath;
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.ArrayList;
import java.util.List;

public class CourseDrawer {
    private MapDrawer mapDrawer;
    private int courseId;
    private AppDatabase appDatabase;
    public CourseDrawer(MapDrawer mapDrawer,int courseId, AppDatabase appDatabase){
        this.mapDrawer=mapDrawer;
        this.courseId=courseId;
        this.appDatabase=appDatabase;
    }
    public void draw(){
        CourseInfo courseInfo=appDatabase.courseInfoDao().loadCourseInfo(courseId)[0];
        CoursePath[] coursePaths=appDatabase.courseDao().loadCourse(courseId);
        List<ScopeDotAddress> scopeDotAddressList=new ArrayList<>();
        for(CoursePath coursePath:coursePaths){
            scopeDotAddressList.add(new ScopeDotAddress(courseInfo.start_longtitude,courseInfo.start_latitude,
                        courseInfo.end_longtitude,courseInfo.end_latitude,coursePath.longtitude,coursePath.latitude));
        }
        mapDrawer.drawCourse(scopeDotAddressList);
    }
}
