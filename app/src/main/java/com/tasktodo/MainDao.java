package com.tasktodo;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {

    // Insert query
    @Insert(onConflict = REPLACE)
    void insert(MainTask mainTask);

    //Delete query

    @Delete
    void delete(MainTask mainTask);

    //Delete all query
    @Delete
    void reset(List<MainTask> mainTask);

    //Update query
    @Query("UPDATE Task SET text = :sText WHERE ID = :sID")
    void update(int sID, String sText);

    //Get all data query
    @Query("SELECT * FROM Task")
    List<MainTask> getAll();
}
