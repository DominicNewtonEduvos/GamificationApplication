package com.example.cognifyjavafiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameText;
    private TextView passwordText;
    private ProgressBar milestoneProgress;
    private TextView milestoneLevelText;
    private Button badgesButton;
    private Button milestonesButton;
    private Button changePasswordButton;
    private ImageView passwordVisibilityToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        setupClickListeners();
        loadUserData();
        loadUserInfo();
    }

    private void initializeViews() {
        usernameText = findViewById(R.id.tv_username);
        passwordText = findViewById(R.id.tv_password);
        milestoneProgress = findViewById(R.id.progress_milestone);
        milestoneLevelText = findViewById(R.id.tv_milestone_level);
        badgesButton = findViewById(R.id.btn_my_badges);
        milestonesButton = findViewById(R.id.btn_milestones);
        changePasswordButton = findViewById(R.id.btn_change_password);
        passwordVisibilityToggle = findViewById(R.id.iv_password_visibility);
    }

    private void setupClickListeners() {
        badgesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, BadgesActivity.class);
                startActivity(intent);
            }
        });

        milestonesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MilestonesActivity.class);
                startActivity(intent);
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }

    private void loadUserData() {
        // Sample user data - in a real app, this would come from a database or shared preferences
        usernameText.setText("Username: Kimberly Sibanda");
        passwordText.setText("testpass@123!");
        milestoneProgress.setProgress(65); // 65% progress
        milestoneLevelText.setText("Level 3 - Advanced Learner");
    }

    private java.util.HashMap<String, String> getUserData() {
        java.util.HashMap<String, String> userData = new java.util.HashMap<>();
        userData.put("username", "Kimberly Sibanda");
        userData.put("email", "kimberly.s@email.com");
        userData.put("studentId", "ST2025001");
        userData.put("milestoneLevel", "Level 3 - Advanced Learner");
        return userData;
    }

    private void loadUserInfo() {
        java.util.HashMap<String, String> user = getUserData();

        TextView usernameView = findViewById(R.id.tv_username);
        TextView emailView = findViewById(R.id.tv_user_email);
        TextView studentIdView = findViewById(R.id.tv_student_id);

        if (usernameView != null) {
            usernameView.setText("Username: " + user.get("username"));
        }
        if (emailView != null) {
            emailView.setText(user.get("email"));
        }
        if (studentIdView != null) {
            studentIdView.setText(user.get("studentId"));
        }

        // Also update milestone text if needed
        if (milestoneLevelText != null) {
            milestoneLevelText.setText(user.get("milestoneLevel"));
        }
    }

    private void togglePasswordVisibility() {
        // Since we're using TextView, we'll just toggle the visibility icon
        // In a real app, you'd use EditText for password input
        if (passwordText.getVisibility() == View.VISIBLE) {
            passwordText.setVisibility(View.GONE);
            passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility_off);
        } else {
            passwordText.setVisibility(View.VISIBLE);
            passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility);
        }
    }
}
