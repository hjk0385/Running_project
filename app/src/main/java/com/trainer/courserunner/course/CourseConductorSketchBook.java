package com.trainer.courserunner.course;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.overseer.CourseOverseerUserRecord;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;
import com.trainer.courserunner.rooms.UserCourse;
import com.trainer.courserunner.rooms.UserCourseDao;
import com.trainer.courserunner.rooms.UserCourseRecordDao;

public class CourseConductorSketchBook extends CourseConductor {
    CourseOverseerUserRecord courseOverseerUserRecord;

    public CourseConductorSketchBook() {
        //데이터베이스
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();

        //코스모드 불러오기
        CourseMode courseMode = appDatabase.courseModeDao().getCourseMode("sketchbook");
        String courseModeName = courseMode.courseModeName;
        long courseModeId = courseMode.courseModeId;

        //유저코스 등록
        UserCourseDao userCourseDao = appDatabase.userCourseDao();
        UserCourse userCourse = new UserCourse();

        userCourseDao.insertDto(userCourse);


        UserCourseRecordDao userCourseRecordDao = appDatabase.userCourseRecordDao();


    }

    @Override
    void oversight() {
        //CourseOverseer 실행
    }

    @Override
    void refreshScreen() {
        //코스 그리기 실행
    }
}
