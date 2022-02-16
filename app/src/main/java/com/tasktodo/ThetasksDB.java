package com.tasktodo;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

// Adding database entities
@Database(entities = {MainTask.class},version = 2,exportSchema = false)
public abstract class ThetasksDB extends RoomDatabase {
    //Create database instance

    private static ThetasksDB database;

    //Define database name

    private static String DATABASE_NAME = "database";

    public synchronized static ThetasksDB getInstance(Context context){
        //Check condition
     if (database == null){
         //When database is empty
         // Initialize database

         database = Room.databaseBuilder(context.getApplicationContext()
                 ,ThetasksDB.class,DATABASE_NAME)
                 .allowMainThreadQueries()
                 .fallbackToDestructiveMigration()
                 .build();
     }
     //Return database
        return database;

    }

    //Create Dao
    public abstract MainDao mainDao();

}


