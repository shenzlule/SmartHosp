package org.hospital.hospitalbookup.ui.pages;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.hospital.hospitalbookup.R;
import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> appointmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        listView = findViewById(R.id.listAppointments);

        appointmentsList = new ArrayList<>();
        appointmentsList.add("Akello Grace - In Person - 10:00 AM");
        appointmentsList.add("Okello Mark - Video Call - 11:30 AM");
        appointmentsList.add("Namakula Rose - In Person - 2:00 PM");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointmentsList);
        listView.setAdapter(adapter);
    }
}
