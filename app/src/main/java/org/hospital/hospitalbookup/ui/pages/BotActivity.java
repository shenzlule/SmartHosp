package org.hospital.hospitalbookup.ui.pages;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.hospital.hospitalbookup.R;

import java.util.ArrayList;
import java.util.Random;

public class BotActivity extends AppCompatActivity {

    private EditText messageInput;
    private Button sendBtn;
    private TextView chatBox;
    private ArrayList<String> replies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);


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
        messageInput = findViewById(R.id.messageInput);
        sendBtn = findViewById(R.id.sendBtn);
        chatBox = findViewById(R.id.chatBox);

        // Predefined responses related to the Remote Patient Monitoring App
        replies = new ArrayList<>();
        replies.add("Welcome! How can I assist you with the Remote Patient Monitoring app?");
        replies.add("This app allows healthcare providers to remotely monitor patients' health data.");
        replies.add("You can track your health metrics such as blood pressure, glucose levels, and more.");
        replies.add("To start, you can connect your health devices via the app's main dashboard.");
        replies.add("You can schedule regular check-ins with your doctor through the app.");
        replies.add("The app provides real-time notifications for medication reminders and health alerts.");
        replies.add("Would you like to know how to integrate a new health device?");
        replies.add("The app also allows for storing and reviewing your health history over time.");
        replies.add("Would you like more details about the security and privacy measures of the app?");
        replies.add("Feel free to ask anything related to your health data, and I’ll be happy to help!");

        sendBtn.setOnClickListener(v -> {
            String msg = messageInput.getText().toString();
            if (!msg.isEmpty()) {
                chatBox.append("You: " + msg + "\n");

                // Check if the message contains keywords and provide a relevant answer
                String reply = getReply(msg);

                chatBox.append("Bot: " + reply + "\n");
                messageInput.setText("");
            }
        });
    }

    private String getReply(String msg) {
        // Simple logic to provide relevant replies based on keywords in the user's message
        if (msg.toLowerCase().contains("track") || msg.toLowerCase().contains("metrics")) {
            return "You can track various health metrics like blood pressure, glucose levels, and weight through the app.";
        } else if (msg.toLowerCase().contains("connect") || msg.toLowerCase().contains("device")) {
            return "To connect a health device, go to the 'Devices' section in the app’s main dashboard and follow the instructions.";
        } else if (msg.toLowerCase().contains("medication")) {
            return "The app provides medication reminders and will notify you when it’s time to take your medication.";
        } else if (msg.toLowerCase().contains("doctor")) {
            return "You can schedule virtual appointments with your doctor via the app’s ‘Appointments’ section.";
        } else if (msg.toLowerCase().contains("history")) {
            return "The app stores your health history, including past measurements, so you can review them whenever you need.";
        } else if (msg.toLowerCase().contains("security") || msg.toLowerCase().contains("privacy")) {
            return "The app uses encryption to protect your data and complies with all relevant privacy regulations to ensure your data is safe.";
        } else {
            // If the message doesn't match any keyword, send a random generic response
            Random random = new Random();
            int randomReplyIndex = random.nextInt(replies.size());
            return replies.get(randomReplyIndex);
        }
    }
}
