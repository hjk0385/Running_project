package com.trainer.courserunner.course;

import android.location.Location;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.CourseDrawerUserCourse;
import com.trainer.courserunner.course.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;
import com.trainer.courserunner.rooms.UserCourseDao;

import java.util.Map;
import java.util.Observable;

public class CourseConductorSketchBook extends CourseConductor {
    Long userCourseId;
    MapDrawer mapDrawer;

    public CourseConductorSketchBook(MapDrawer mapDrawer) {
        //데이터베이스
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        //코스모드 불러오기
        CourseMode courseMode = appDatabase.courseModeDao().getCourseMode("sketchbook");
        //유저코스 등록
        UserCourseDao userCourseDao = appDatabase.userCourseDao();
        UserCourse userCourse = new UserCourse();
        userCourse.courseId = null;
        userCourse.userCourseId = null;
        userCourse.courseModeId = courseMode.courseModeId;
        userCourse.userCourseName = null;
        this.userCourseId = userCourseDao.insertDto(userCourse);
        this.mapDrawer=mapDrawer;
    }

    Location currentlocation=null;
    @Override
    public void update(Observable observable, Object o) {
        Location location = (Location) o;
        if(currentlocation==null||checkUpdateDistance(currentlocation,location)){
            CourseOverseerUserRecord courseOverseerUserRecord= new CourseOverseerUserRecord(userCourseId);
            CourseDrawerUserCourse courseDrawerUserCourse = new CourseDrawerUserCourse(mapDrawer, userCourseId);
            //연계
            courseOverseerUserRecord.sellSubscription(courseDrawerUserCourse);
            courseOverseerUserRecord.update(null, location);
        }
    }
}




