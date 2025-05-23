package org.hospital.hospitalbookup.ui.pages;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    RadioGroup radioUserType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.deepcayn));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.deepcayn));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }

        radioUserType = findViewById(R.id.radioUserType);

        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.btnRegister);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        alreadyHaveAccount.setOnClickListener(v -> goToLoginScreen());

        btnRegister.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            int selectedRoleId = radioUserType.getCheckedRadioButtonId();
            String userRole = selectedRoleId == R.id.radioDoctor ? "Doctor" : "Patient";


            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Executors.newSingleThreadExecutor().execute(() -> {
                if (db.userDao().findByEmailAndRole(userEmail,userRole) == null) {
                    db.userDao().insert(new User() {{
                        email = userEmail;
                        password = userPassword;
                        role = userRole;  // ⬅ Save the role!
                    }});
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }

    private void goToLoginScreen() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
