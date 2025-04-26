package org.hospital.hospitalbookup.ui.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.hospital.hospitalbookup.ui.dao.UserDao;
import org.hospital.hospitalbookup.ui.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "smartcash_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
