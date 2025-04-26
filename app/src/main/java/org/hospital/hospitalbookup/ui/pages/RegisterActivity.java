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

import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    EditText email, password;
    Button btnRegister;

    TextView alreadyHaveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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


        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.btnRegister);



        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        // Set OnClickListener for Login link
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Login screen
                goToLoginScreen();
            }
        });
        btnRegister.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Executors.newSingleThreadExecutor().execute(() -> {
                if (db.userDao().findByEmail(userEmail) == null) {
                    db.userDao().insert(new User() {{
                        email = userEmail;
                        password = userPassword;
                    }});
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }


    // Method to navigate to Login screen
    private void goToLoginScreen() {
        // Start the Login Activity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); // Replace with actual LoginActivity class
        startActivity(intent);
        finish();
    }
}
