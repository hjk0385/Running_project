package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courseinfo")
public class CourseInfo {
    @PrimaryKey
    public int course_id;
    public double start_latitude;
    public double start_longtitude;
    public double end_latitude;
    public double end_longtitude;
}
