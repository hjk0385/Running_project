package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mapflag")
public class MapFlag {
    @PrimaryKey(autoGenerate = true)
    public long mapflag_id;
    public double latitude;
    public double longitude;
}
