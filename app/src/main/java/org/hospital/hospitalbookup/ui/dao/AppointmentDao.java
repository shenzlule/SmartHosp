package org.hospital.hospitalbookup.ui.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.hospital.hospitalbookup.ui.models.Appointment;

import java.util.List;

@Dao
public interface AppointmentDao {
    @Insert
    void insert(Appointment appointment);

    @Query("SELECT * FROM Appointment")
    List<Appointment> getAll();
}
