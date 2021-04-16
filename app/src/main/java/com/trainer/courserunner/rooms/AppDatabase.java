package com.trainer.courserunner.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CourseInfo.class, CoursePath.class,
        UserCourseInfo.class, UserCoursePath.class, UserLocationPath.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseInfoDao courseInfoDao();

    public abstract CoursePathDao coursePathDao();

    public abstract UserCourseInfoDao userCourseInfoDao();

    public abstract UserCoursePathDao userCoursePathDao();

    public abstract UserLocationPathDao userLocationPathDao();
}
