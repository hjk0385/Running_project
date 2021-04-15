package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "coursepath",
        foreignKeys = {@ForeignKey(
                                entity = CourseInfo.class,
                                parentColumns = "course_id",
                                childColumns = "course_id",
                                onDelete = ForeignKey.CASCADE)
                        })
public class CoursePath {
    @PrimaryKey
    public int course_id;
    public int coordinate_number;
    public double latitude;
    public double longtitude;
}
