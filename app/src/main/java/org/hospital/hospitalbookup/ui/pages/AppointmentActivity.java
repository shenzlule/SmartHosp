package org.hospital.hospitalbookup.ui.pages;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.database.AppointmentDatabase;
import org.hospital.hospitalbookup.ui.models.Appointment;

public class AppointmentActivity extends AppCompatActivity {

    private EditText editTextPatientName, editTextDoctorName, editTextDate;
    private Button buttonBook;
    private AppointmentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        // Set status bar color to deep cyan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.deepcayn)); // Deep cyan

        }
        // Make sure status bar icons are white (default for dark background)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0); // No LIGHT_STATUS_BAR flag
        }

        editTextPatientName = findViewById(R.id.editTextPatientName);
        editTextDoctorName = findViewById(R.id.editTextDoctorName);
        editTextDate = findViewById(R.id.editTextDate);
        buttonBook = findViewById(R.id.buttonBook);

        db = Room.databaseBuilder(getApplicationContext(),
                AppointmentDatabase.class, "appointments_db").allowMainThreadQueries().build();

        buttonBook.setOnClickListener(v -> {
            String patient = editTextPatientName.getText().toString();
            String doctor = editTextDoctorName.getText().toString();
            String date = editTextDate.getText().toString();

            db.appointmentDao().insert(new Appointment(patient, doctor, date));
            Toast.makeText(this, "Appointment Booked!", Toast.LENGTH_SHORT).show();
        });
    }
}
