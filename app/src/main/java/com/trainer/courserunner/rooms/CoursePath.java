package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "coursepath",
        primaryKeys = {
                "course_id",
                "coursepath_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = CourseInfo.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class CoursePath {
    public long course_id;
    public long coursepath_id;
    public double latitude;
    public double longtitude;
}
