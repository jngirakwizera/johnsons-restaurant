package com.johnsons.restaurant;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MenuDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MenuItem menuItem);


    @Query("SELECT * FROM menuitem ORDER BY name ASC")
    List<MenuItem> getMenuItems();
}