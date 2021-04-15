package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "usercoursepath",
        foreignKeys = {@ForeignKey(
                                    entity = CoursePath.class,
                                    parentColumns = "course_id",
                                    childColumns = "course_id",
                                    onDelete = ForeignKey.CASCADE)}
        )
public class UserCoursePath {
    //primarykeys
    @PrimaryKey
    public int user_courseid;
    @PrimaryKey
    @ColumnInfo(name="course_id")
    public double course_id;
    @PrimaryKey
    @ColumnInfo(name="coordinate_number")
    public int coordinate_number;
    //data
    @ColumnInfo(name="start_latitude")
    public double start_latitude;
    @ColumnInfo(name="start_longtitude")
    public double start_longtitude;
    @ColumnInfo(name="end_latitude")
    public double end_latitude;
    @ColumnInfo(name="end_longtitude")
    public double end_longtitude;
}
