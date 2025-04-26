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
import org.hospital.hospitalbookup.ui.database.VitalsDatabase;
import org.hospital.hospitalbookup.ui.models.Vitals;

public class VitalsActivity extends AppCompatActivity {
    private EditText heartRateInput, bpInput, spo2Input;
    private Button saveBtn;
    private VitalsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);




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

        heartRateInput = findViewById(R.id.heartRate);
        bpInput = findViewById(R.id.bloodPressure);
        spo2Input = findViewById(R.id.spo2);
        saveBtn = findViewById(R.id.saveVitals);

        db = Room.databaseBuilder(getApplicationContext(),
                VitalsDatabase.class, "vitals_db").allowMainThreadQueries().build();

        saveBtn.setOnClickListener(v -> {
            db.vitalsDao().insert(new Vitals(
                    heartRateInput.getText().toString(),
                    bpInput.getText().toString(),
                    spo2Input.getText().toString()
            ));
            Toast.makeText(this, "Vitals Saved", Toast.LENGTH_SHORT).show();
        });
    }
}
