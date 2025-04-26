package org.hospital.hospitalbookup.ui.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.hospital.hospitalbookup.ui.models.Vitals;

import java.util.List;

@Dao
public interface VitalsDao {
    @Insert
    void insert(Vitals vitals);

    @Query("SELECT * FROM Vitals")
    List<Vitals> getAllVitals();
}
