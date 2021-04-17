package com.trainer.courserunner.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CourseInfo.class, CourseFlag.class, MapFlag.class,
        UserCourseInfo.class,
        UserLocationRecord.class, UserMapFlag.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //course
    public abstract CourseInfoDao courseInfoDao();

    public abstract CourseFlagDao courseFlagDao();

    public abstract MapFlagDao mapFlagDao();

    public abstract CourseDao courseDao();

    //user
    public abstract UserCourseInfoDao userCourseInfoDao();

    public abstract UserLocationRecordDao userLocationRecordDao();

    public abstract UserMapFlagDao userMapFlagDao();

    public abstract UserCourseDao userCourseDao();
}
