package org.hospital.hospitalbookup.ui.pages;


import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.database.VitalsDatabase;
import org.hospital.hospitalbookup.ui.models.Vitals;

import java.util.ArrayList;
import java.util.List;

public class ViewVitalsActivity extends AppCompatActivity {
    private ListView vitalsListView;
    private VitalsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vitals);


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
        vitalsListView = findViewById(R.id.vitalsListView);

        db = Room.databaseBuilder(getApplicationContext(),
                VitalsDatabase.class, "vitals_db").allowMainThreadQueries().build();

        List<Vitals> vitalsList = db.vitalsDao().getAllVitals();

        List<String> displayList = new ArrayList<>();
        for (Vitals v : vitalsList) {
            displayList.add("Heart Rate: " + v.heartRate + " | BP: " + v.bloodPressure + " | SpO2: " + v.spo2);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        vitalsListView.setAdapter(adapter);
    }
}
