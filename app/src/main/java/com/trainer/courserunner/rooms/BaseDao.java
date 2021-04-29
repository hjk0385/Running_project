package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface BaseDao<T> {
    @Insert
    void insertDto(T dto);
}
