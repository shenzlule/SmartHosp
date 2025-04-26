package org.hospital.hospitalbookup.ui.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.hospital.hospitalbookup.ui.dao.AppointmentDao;
import org.hospital.hospitalbookup.ui.models.Appointment;

@Database(entities = {Appointment.class}, version = 1)
public abstract class AppointmentDatabase extends RoomDatabase {
    public abstract AppointmentDao appointmentDao();
}
