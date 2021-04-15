package com.trainer.courserunner.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CoursePath.class, UserCoursePath.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CoursePathDao courseDao();
}
