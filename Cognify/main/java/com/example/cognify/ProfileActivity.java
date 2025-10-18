package com.example.cognify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView usernameText;
    private TextView passwordText;
    private ProgressBar milestoneProgress;
    private TextView milestoneLevelText;
    private TextView xpProgressText;
    private Button badgesButton;
    private Button milestonesButton;
    private Button changePasswordButton;
    private Button btnAddViewNotes;
    private ImageView passwordVisibilityToggle;
    
    private MilestoneManager milestoneManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        milestoneManager = new MilestoneManager();
        
        initializeViews();
        setupClickListeners();
        loadUserData();
        loadUserInfo();
        updateMilestoneDisplay();
        updateBadgeButtonColor();
    }

    private void initializeViews() {
        usernameText = findViewById(R.id.tv_username);
        passwordText = findViewById(R.id.tv_password);
        milestoneProgress = findViewById(R.id.progress_milestone);
        milestoneLevelText = findViewById(R.id.tv_milestone_level);
//        xpProgressText = findViewById(R.id.tv_xp_progress);
        badgesButton = findViewById(R.id.btn_my_badges);
        milestonesButton = findViewById(R.id.btn_milestones);
        changePasswordButton = findViewById(R.id.btn_change_password);
        btnAddViewNotes = findViewById(R.id.btnAddViewNotes);
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

        if (passwordVisibilityToggle != null) {
            passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    togglePasswordVisibility();
                }
            });
        }

        btnAddViewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AddAndViewInformation.class);
                startActivity(intent);
            }
        });

    }

    private void loadUserData() {
        // Sample user data
        usernameText.setText("Username: Kimberly Sibanda");
        if (passwordText != null) {
            passwordText.setText("testpass@123!");
        }
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
    }

    private void updateMilestoneDisplay() {
        int currentXP = UserProgress.getCurrentXP();
        Milestone currentMilestone = milestoneManager.getCurrentMilestone(currentXP);
        int progress = milestoneManager.calculateProgress(currentXP, currentMilestone);
        
        // Update progress bar
        if (milestoneProgress != null) {
            milestoneProgress.setProgress(progress);
        }
        
        // Update milestone level text
        if (milestoneLevelText != null) {
            milestoneLevelText.setText("Level " + currentMilestone.getLevel() + " - " + currentMilestone.getTitle());
        }
        
        // Update XP progress text (only if it exists in your layout)
        if (xpProgressText != null) {
            xpProgressText.setText("XP: " + currentXP + " / " + currentMilestone.getMaxXP());
        }
    }

    private void updateBadgeButtonColor() {
        if (badgesButton == null) return;
        
        int currentXP = UserProgress.getCurrentXP();
        Milestone currentMilestone = milestoneManager.getCurrentMilestone(currentXP);
        int level = currentMilestone.getLevel();
        
        // Only update if the drawable resources exist
        try {
            switch (level) {
                case 1:
//                    badgesButton.setBackgroundResource(R.drawable.button_milestone_level1);
                    break;
                case 2:
//                    badgesButton.setBackgroundResource(R.drawable.button_milestone_level2);
                    break;
                case 3:
//                    badgesButton.setBackgroundResource(R.drawable.button_milestone_level3);
                    break;
                case 4:
//                    badgesButton.setBackgroundResource(R.drawable.button_milestone_level4);
                    break;
                case 5:
//                    badgesButton.setBackgroundResource(R.drawable.button_milestone_level5);
                    break;
            }
        } catch (Exception e) {
            // Drawables don't exist yet, skip color update
        }
    }

    private void togglePasswordVisibility() {
        if (passwordText == null) return;
        
        if (passwordText.getVisibility() == View.VISIBLE) {
            passwordText.setVisibility(View.GONE);
            if (passwordVisibilityToggle != null) {
                passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility_off);
            }
        } else {
            passwordText.setVisibility(View.VISIBLE);
            if (passwordVisibilityToggle != null) {
                passwordVisibilityToggle.setImageResource(R.drawable.ic_visibility);
            }
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateMilestoneDisplay();
        updateBadgeButtonColor();
    }
}