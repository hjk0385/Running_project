package com.trainer.courserunner.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CourseInfo.class, CoursePath.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseInfoDao courseInfoDao();
    public abstract CoursePathDao coursePathDao();
}
