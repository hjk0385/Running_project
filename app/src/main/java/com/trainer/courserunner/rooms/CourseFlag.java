package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "courseflag",
        primaryKeys = {
                "course_id",
                "mapflag_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = CourseInfo.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = MapFlag.class,
                        parentColumns = "mapflag_id",
                        childColumns = "mapflag_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class CourseFlag {
    public long course_id;
    public long mapflag_id;
    public long courseflag_order;
}
