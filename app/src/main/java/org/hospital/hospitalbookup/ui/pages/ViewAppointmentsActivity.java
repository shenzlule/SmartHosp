package org.hospital.hospitalbookup.ui.pages;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.database.AppointmentDatabase;
import org.hospital.hospitalbookup.ui.models.Appointment;
import java.util.ArrayList;
import java.util.List;

public class ViewAppointmentsActivity extends AppCompatActivity {

    private ListView listViewAppointments;
    private AppointmentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        // Set status bar color to deep cyan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.deepcayn));
        }
        // White status bar icons
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }

        listViewAppointments = findViewById(R.id.listViewAppointments);
        db = Room.databaseBuilder(getApplicationContext(),
                AppointmentDatabase.class, "appointments_db").allowMainThreadQueries().build();

        loadAppointments();
    }

    private void loadAppointments() {
        List<Appointment> appointments = db.appointmentDao().getAllAppointments();
        List<String> displayList = new ArrayList<>();

        for (Appointment appointment : appointments) {
            displayList.add(
                    "🧑 Patient: " + appointment.getPatientName() + "\n" +
                            "🩺 Doctor: " + appointment.getDoctorName() + "\n" +
                            "📅 Time: " + appointment.getDate()
            );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);

        listViewAppointments.setAdapter(adapter);
    }
}
