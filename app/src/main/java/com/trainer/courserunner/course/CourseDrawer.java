package com.trainer.courserunner.course;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CoursePath;

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
        CoursePath[] coursePaths=appDatabase.courseDao().loadCourse(courseId);
        
    }
}
