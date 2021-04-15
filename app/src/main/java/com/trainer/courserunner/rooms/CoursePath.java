package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coursepath")
public class CoursePath {
    @PrimaryKey
    public int course_id;
    @ColumnInfo(name="coordinate_number")
    public int coordinate_number;
    @ColumnInfo(name="latitude")
    public double latitude;
    @ColumnInfo(name="longtitude")
    public double longtitude;
}
