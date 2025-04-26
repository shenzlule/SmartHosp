package org.hospital.hospitalbookup.ui.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vitals {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String heartRate;
    public String bloodPressure;
    public String spo2;

    public Vitals(String heartRate, String bloodPressure, String spo2) {
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.spo2 = spo2;
    }
}

