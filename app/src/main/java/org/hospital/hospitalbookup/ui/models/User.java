package org.hospital.hospitalbookup.ui.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "email")
    public String email;


    public String password;

    @ColumnInfo(name = "role")
    public String role;  // <-- NEW Field to save "Patient" or "Doctor"
}
