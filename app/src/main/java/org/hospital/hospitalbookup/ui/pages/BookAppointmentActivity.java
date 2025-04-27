package org.hospital.hospitalbookup.ui.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.hospital.hospitalbookup.R;
import java.util.*;

public class BookAppointmentActivity extends AppCompatActivity {

    Spinner spinnerDoctor, spinnerTime;
    Button btnConfirm;

    Map<String, List<String>> doctorTimes; // Map Doctor -> Times

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        spinnerDoctor = findViewById(R.id.spinnerDoctor_);
        spinnerTime = findViewById(R.id.spinnerTime);
        btnConfirm = findViewById(R.id.btnConfirmAppointment);

        // Setup Doctor and their available times
        setupDoctorTimes();

        ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(doctorTimes.keySet()));
        spinnerDoctor.setAdapter(doctorAdapter);

        // Listener: when Doctor is selected, update times
        spinnerDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDoctor = parent.getItemAtPosition(position).toString();
                List<String> times = doctorTimes.get(selectedDoctor);

                ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(BookAppointmentActivity.this, android.R.layout.simple_spinner_item, times);
                spinnerTime.setAdapter(timeAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnConfirm.setOnClickListener(v -> {
            String doctor = spinnerDoctor.getSelectedItem().toString();
            String time = spinnerTime.getSelectedItem().toString();
            Toast.makeText(BookAppointmentActivity.this, "Appointment with " + doctor + " at " + time + " booked!", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void setupDoctorTimes() {
        doctorTimes = new HashMap<>();

        // Example Doctors and Available Times
        doctorTimes.put("Dr. Namaganda Sarah", Arrays.asList("9:00 AM", "10:30 AM", "2:00 PM"));
        doctorTimes.put("Dr. Okello Mark", Arrays.asList("11:00 AM", "1:00 PM", "3:00 PM"));
        doctorTimes.put("Dr. Lule David", Arrays.asList("10:00 AM", "12:00 PM", "4:00 PM"));
        doctorTimes.put("Dr. Akello Grace", Arrays.asList("8:30 AM", "11:30 AM", "2:30 PM"));
        doctorTimes.put("Dr. Kato Brian", Arrays.asList("9:30 AM", "1:30 PM", "3:30 PM"));
    }
}
