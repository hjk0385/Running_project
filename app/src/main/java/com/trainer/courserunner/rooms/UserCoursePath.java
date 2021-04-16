package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "usercoursepath",
        primaryKeys = {
                "usercourse_id",
                "coursepath_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourseInfo.class,
                        parentColumns = "usercourse_id",
                        childColumns = "usercourse_id",
                        onDelete = ForeignKey.CASCADE
                )
        }
        )
public class UserCoursePath {
    public long usercourse_id;
    public long coursepath_id;
}
