package com.johnsons.restaurant;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MenuItem.class}, version = 1, exportSchema = false)
public abstract class MenuDatabase extends RoomDatabase {

    public abstract MenuDao wordDao();

    private static volatile MenuDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MenuDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MenuDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MenuDatabase.class, "menuitem")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}