package com.trainer.courserunner.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CourseInfo.class, CourseFlag.class, MapFlag.class,
        UserCourseInfo.class,
        UserLocationRecord.class, UserMapFlag.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();

    public abstract UserCourseDao userCourseDao();
}
