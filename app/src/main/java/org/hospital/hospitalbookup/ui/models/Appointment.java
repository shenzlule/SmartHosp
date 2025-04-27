package org.hospital.hospitalbookup.ui.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String patientName;
    private String doctorName;
    private String date;

    // Required empty constructor
    public Appointment() {
    }

    public Appointment(String patientName, String doctorName, String date) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
