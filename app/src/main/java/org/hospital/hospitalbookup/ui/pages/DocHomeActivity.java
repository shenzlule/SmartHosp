package org.hospital.hospitalbookup.ui.pages;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.session.SessionManager;


public class DocHomeActivity extends AppCompatActivity {

    Button addNoteBtn;

    SessionManager sessionManager;
    TextView welcomeText;

    Button needhelp;
    TextView logoutBtn;

    LinearLayout totalAppointmentsTile, inPersonTile, videoTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_home);


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


        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        welcomeText = findViewById(R.id.welcome_Text);


        welcomeText.setText("Welcome, " + sessionManager.getUserEmail());

        logoutBtn = findViewById(R.id.log_out);

        logoutBtn.setOnClickListener(v -> {
            sessionManager.logout();
            startActivity(new Intent(DocHomeActivity.this, LoginActivity.class));
            finish();
        });




        addNoteBtn = findViewById(R.id.docAddNoteBtn);
        totalAppointmentsTile = findViewById(R.id.docTotalAppointmentsTile);
        inPersonTile = findViewById(R.id.docInPersonTile);
        videoTile = findViewById(R.id.docVideoTile);

        addNoteBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DocHomeActivity.this, DocAddNoteActivity.class);
            startActivity(intent);
        });

        totalAppointmentsTile.setOnClickListener(v -> {
            Intent intent = new Intent(DocHomeActivity.this, DocAppointmentsListActivity.class);
            startActivity(intent);
        });

        inPersonTile.setOnClickListener(v -> {
            Intent intent = new Intent(DocHomeActivity.this, DocAppointmentsListActivity.class);
            startActivity(intent);
        });

        videoTile.setOnClickListener(v -> {
            Intent intent = new Intent(DocHomeActivity.this, DocAppointmentsListActivity.class);
            startActivity(intent);
        });
    }
}
