package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "usercoursepath",
        foreignKeys = {@ForeignKey(
                                    entity = CourseInfo.class,
                                    parentColumns = "course_id",
                                    childColumns = "course_id",
                                    onDelete = ForeignKey.CASCADE)}
        )
public class UserCoursePath {
    @PrimaryKey
    public int user_courseid;
    @PrimaryKey
    public double course_id;
    @PrimaryKey
    public int coordinate_number;
    //data
    public double start_latitude;
    public double start_longtitude;
    public double end_latitude;
    public double end_longtitude;
}
