package org.hospital.hospitalbookup.ui.pages;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.hospital.hospitalbookup.R;
import org.hospital.hospitalbookup.ui.database.AppDatabase;
import org.hospital.hospitalbookup.ui.models.User;
import org.hospital.hospitalbookup.ui.session.SessionManager;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnLogin;

    TextView noAccount;





    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());

        // Set status bar color to deep cyan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.deepcayn)); // Deep cyan
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.deepcayn));


        }
        // Make sure status bar icons are white (default for dark background)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0); // No LIGHT_STATUS_BAR flag
        }


        // If user is already logged in, skip login
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        noAccount = findViewById(R.id.noAccount);
        btnLogin = findViewById(R.id.btnLogin);

        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);


        // Set OnClickListener for Login link
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Login screen
                goToRegisterScreen();
            }
        });

        btnLogin.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Executors.newSingleThreadExecutor().execute(() -> {
                User user = db.userDao().login(userEmail, userPassword);
                runOnUiThread(() -> {
                    if (user != null) {
                        sessionManager.login(userEmail); // Save session
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }

    // Method to navigate to Register screen
    private void goToRegisterScreen() {
        // Start the Login Activity
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
