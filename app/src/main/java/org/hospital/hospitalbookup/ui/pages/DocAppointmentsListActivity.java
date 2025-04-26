package org.hospital.hospitalbookup.ui.pages;


import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.hospital.hospitalbookup.R;

public class DocAppointmentsListActivity extends AppCompatActivity {

    private ListView listView;
    private String[] mockAppointments = {
            "Okello Peter - 9:00 AM",
            "Namulondo Grace - 9:45 AM",
            "Muwanga Brian - 10:30 AM",
            "Achieng Esther - 11:15 AM",
            "Mugisha Daniel - 12:00 PM",
            "Nabirye Rose - 1:30 PM",
            "Kaggwa Samuel - 2:15 PM",
            "Ayo Brenda - 3:00 PM",
            "Waiswa Henry - 3:45 PM",
            "Nampiima Lydia - 4:30 PM"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointments_list);

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

        listView = findViewById(R.id.docAppointmentsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mockAppointments);
        listView.setAdapter(adapter);
    }
}

