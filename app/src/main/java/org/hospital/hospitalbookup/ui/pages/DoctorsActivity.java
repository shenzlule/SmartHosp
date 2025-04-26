package org.hospital.hospitalbookup.ui.pages;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.adapters.DoctorAdapter;
import org.hospital.hospitalbookup.ui.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorsActivity extends AppCompatActivity {

    ListView doctorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

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


        doctorListView = findViewById(R.id.doctorListView);

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Dr. Amina Yusuf", "Cardiologist", R.drawable.doc1));
        doctorList.add(new Doctor("Dr. Brian Kim", "Neurologist", R.drawable.doc2));
        doctorList.add(new Doctor("Dr. Clara Lee", "Dermatologist", R.drawable.doc3));
        doctorList.add(new Doctor("Dr. Daniel Cruz", "Pediatrician", R.drawable.doc1));
        doctorList.add(new Doctor("Dr. Elena Novak", "Psychiatrist", R.drawable.doc2));
        doctorList.add(new Doctor("Dr. Farid Ahmed", "Orthopedic", R.drawable.doc3));
        doctorList.add(new Doctor("Dr. Grace Tan", "Ophthalmologist", R.drawable.doc1));
        doctorList.add(new Doctor("Dr. Henry Brooks", "ENT Specialist", R.drawable.doc2));

        DoctorAdapter adapter = new DoctorAdapter(this, doctorList);
        doctorListView.setAdapter(adapter);
    }
}

