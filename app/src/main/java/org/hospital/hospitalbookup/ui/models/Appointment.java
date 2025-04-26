package org.hospital.hospitalbookup.ui.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String patientName;
    public String doctorName;
    public String date;

    public Appointment(String patientName, String doctorName, String date) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
    }
}

