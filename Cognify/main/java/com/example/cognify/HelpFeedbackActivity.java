package com.example.cognify;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class HelpFeedbackActivity extends AppCompatActivity {

    private EditText helpInput, feedbackInput;
    private Button helpCancelBtn, helpSubmitBtn, feedbackCancelBtn, feedbackSubmitBtn;

    private DatabaseReference helpRef, feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_feeback); // replace with your XML name

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        helpRef = database.getReference("help_requests");
        feedbackRef = database.getReference("feedbacks");

        // Find views
        helpInput = findViewById(R.id.helpInput);
        feedbackInput = findViewById(R.id.feedbackInput);
        helpCancelBtn = findViewById(R.id.helpCancelBtn);
        helpSubmitBtn = findViewById(R.id.helpSubmitBtn);
        feedbackCancelBtn = findViewById(R.id.feedbackCancelBtn);
        feedbackSubmitBtn = findViewById(R.id.feedbackSubmitBtn);

        // Disable submit buttons initially
        helpSubmitBtn.setEnabled(false);
        feedbackSubmitBtn.setEnabled(false);

        // Enable submit when user types
        helpInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                helpSubmitBtn.setEnabled(s.toString().trim().length() > 0);
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        feedbackInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                feedbackSubmitBtn.setEnabled(s.toString().trim().length() > 0);
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Cancel buttons
        helpCancelBtn.setOnClickListener(v -> helpInput.getText().clear());
        feedbackCancelBtn.setOnClickListener(v -> feedbackInput.getText().clear());

        // Submit buttons store text in Firebase
        helpSubmitBtn.setOnClickListener(v -> {
            String helpText = helpInput.getText().toString().trim();
            if(!helpText.isEmpty()){
                String id = helpRef.push().getKey();
                Map<String, Object> helpData = new HashMap<>();
                helpData.put("text", helpText);
                helpData.put("timestamp", System.currentTimeMillis());

                helpRef.child(id).setValue(helpData);
                Toast.makeText(this, "Help submitted!", Toast.LENGTH_SHORT).show();
                helpInput.getText().clear();
            }
        });

        feedbackSubmitBtn.setOnClickListener(v -> {
            String feedbackText = feedbackInput.getText().toString().trim();
            if(!feedbackText.isEmpty()){
                String id = feedbackRef.push().getKey();
                Map<String, Object> feedbackData = new HashMap<>();
                feedbackData.put("text", feedbackText);
                feedbackData.put("timestamp", System.currentTimeMillis());

                feedbackRef.child(id).setValue(feedbackData);
                Toast.makeText(this, "Feedback submitted!", Toast.LENGTH_SHORT).show();
                feedbackInput.getText().clear();
            }
        });
    }
}
