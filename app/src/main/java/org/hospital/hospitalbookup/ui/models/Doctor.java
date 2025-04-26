package org.hospital.hospitalbookup.ui.models;

public class Doctor {
    String name;
    String specialty;
    int imageResId;

    public Doctor(String name, String specialty, int imageResId) {
        this.name = name;
        this.specialty = specialty;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public int getImageResId() { return imageResId; }
}

