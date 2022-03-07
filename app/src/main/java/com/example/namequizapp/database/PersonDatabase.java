package com.example.namequizapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDao personDao();

    private static PersonDatabase INSTANCE;

    public static PersonDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PersonDatabase.class,
                    "person_database"
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
