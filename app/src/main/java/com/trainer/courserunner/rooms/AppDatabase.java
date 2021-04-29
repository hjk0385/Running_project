package com.trainer.courserunner.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Course.class, CourseFlag.class, CourseMode.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract CourseFlagDao courseFlagDao();
    public abstract CourseModeDao courseModeDao();
}
