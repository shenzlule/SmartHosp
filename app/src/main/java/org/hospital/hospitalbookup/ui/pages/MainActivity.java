package org.hospital.hospitalbookup.ui.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.session.SessionManager;

public class MainActivity extends AppCompatActivity {


    SessionManager sessionManager;
    TextView welcomeText;

    Button needhelp;
    TextView logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        welcomeText = findViewById(R.id.welcomeText);


        welcomeText.setText("Welcome, " + sessionManager.getUserEmail());

        logoutBtn = findViewById(R.id.logout);

        logoutBtn.setOnClickListener(v -> {
            sessionManager.logout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });


        findViewById(R.id.layoutAppointment).setOnClickListener(v ->
                    startActivity(new Intent(this, ViewAppointmentsActivity.class)));

        findViewById(R.id.BookBtn).setOnClickListener(v ->
                startActivity(new Intent(this, AppointmentActivity.class)));

        findViewById(R.id.layoutBot).setOnClickListener(v ->
                    startActivity(new Intent(this, BotActivity.class)));

            findViewById(R.id.layoutPayment).setOnClickListener(v ->
                    startActivity(new Intent(this, PaymentActivity.class)));

            findViewById(R.id.layoutVitals).setOnClickListener(v ->
                    startActivity(new Intent(this, VitalsActivity.class)));

            findViewById(R.id.layoutViewVitals).setOnClickListener(v ->
                    startActivity(new Intent(this, ViewVitalsActivity.class)));


            findViewById(R.id.layoutRecords).setOnClickListener(v ->
                    startActivity(new Intent(this, RecordsActivity.class)));

            findViewById(R.id.layoutChat).setOnClickListener(v ->
                    startActivity(new Intent(this, ChatActivity.class)));

        findViewById(R.id.docs).setOnClickListener(v ->
                startActivity(new Intent(this, DoctorsActivity.class)));

        findViewById(R.id.about).setOnClickListener(v ->
                startActivity(new Intent(this, AboutActivity.class)));

    }
}
