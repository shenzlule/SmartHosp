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

public class DocAddNoteActivity extends AppCompatActivity {

    private EditText noteInput;
    private Button saveNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_add_note);

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

        noteInput = findViewById(R.id.docNoteInput);
        saveNoteBtn = findViewById(R.id.docSaveNoteBtn);

        saveNoteBtn.setOnClickListener(v -> {
            String note = noteInput.getText().toString().trim();
            if (!note.isEmpty()) {
                Toast.makeText(this, "Note Saved: " + note, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please write a note", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

