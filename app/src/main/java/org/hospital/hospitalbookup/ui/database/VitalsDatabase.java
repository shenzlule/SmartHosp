package org.hospital.hospitalbookup.ui.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.hospital.hospitalbookup.ui.dao.VitalsDao;
import org.hospital.hospitalbookup.ui.models.Vitals;

@Database(entities = {Vitals.class}, version = 1)
public abstract class VitalsDatabase extends RoomDatabase {
    public abstract VitalsDao vitalsDao();
}
