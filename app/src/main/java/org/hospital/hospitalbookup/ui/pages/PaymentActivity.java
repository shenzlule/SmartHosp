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

import org.hospital.hospitalbookup.R;

public class PaymentActivity extends AppCompatActivity {
    EditText patientName, amount;
    Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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
        patientName = findViewById(R.id.paymentPatientName);
        amount = findViewById(R.id.paymentAmount);
        payBtn = findViewById(R.id.makePayment);

        payBtn.setOnClickListener(v -> {
            // simulate payment processing
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
        });
    }
}

