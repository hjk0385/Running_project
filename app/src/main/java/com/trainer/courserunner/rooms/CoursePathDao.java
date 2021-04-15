package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CoursePathDao {
    @Insert
    public long insertCoursePath(CoursePath coursePaths);
}
