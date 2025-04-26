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

public class ChatActivity extends AppCompatActivity {

    private EditText messageInput;
    private Button sendBtn;
    private TextView chatBox;
    private ArrayList<String> replies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


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

        // List of 10 pre-defined reply messages
        replies = new ArrayList<>();
        replies.add("Hello! How can I assist you today?");
        replies.add("I'm here to help you!");
        replies.add("Sure, what do you need?");
        replies.add("Can you clarify that a little?");
        replies.add("I’m processing your request.");
        replies.add("What else can I help with?");
        replies.add("Let me find that information for you.");
        replies.add("I will get back to you in a moment.");
        replies.add("Could you provide more details?");
        replies.add("Thank you for your patience.");

        sendBtn.setOnClickListener(v -> {
            String msg = messageInput.getText().toString();
            if (!msg.isEmpty()) {
                chatBox.append("You: " + msg + "\n");

                // Get a random reply from the list
                Random random = new Random();
                int randomReplyIndex = random.nextInt(replies.size());
                String reply = replies.get(randomReplyIndex);

                chatBox.append("Bot: " + reply + "\n");
                messageInput.setText("");
            }
        });
    }
}

