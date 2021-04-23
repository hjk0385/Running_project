package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courseinfo")
public class CourseInfo {
    @PrimaryKey(autoGenerate = true)
    public long course_id;
}
