package com.example.cognifyjavafiles;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MilestonesActivity extends AppCompatActivity {

    private ListView milestonesListView;
    private TextView currentLevelText;
    private TextView totalXpText;
    private MilestoneAdapter milestoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestones);

        initializeViews();
        setupMilestones();
    }

    private void initializeViews() {
        milestonesListView = findViewById(R.id.list_milestones);
        currentLevelText = findViewById(R.id.tv_current_level);
        totalXpText = findViewById(R.id.tv_total_xp);
    }

    private void setupMilestones() {
        List<Milestone> milestones = createSampleMilestones();
        milestoneAdapter = new MilestoneAdapter(this, milestones);
        milestonesListView.setAdapter(milestoneAdapter);

        // Sample user progress
        currentLevelText.setText("Current Level: 3");
        totalXpText.setText("Total XP: 2,450 / 3,000");
    }

    private List<Milestone> createSampleMilestones() {
        List<Milestone> milestones = new ArrayList<>();
        
        milestones.add(new Milestone("Beginner", "Complete your first lesson", 100, true, "Completed"));
        milestones.add(new Milestone("Getting Started", "Complete 5 lessons", 250, true, "Completed"));
        milestones.add(new Milestone("Regular Learner", "Complete 10 lessons", 500, true, "Completed"));
        milestones.add(new Milestone("Dedicated Student", "Complete 25 lessons", 750, true, "Completed"));
        milestones.add(new Milestone("Advanced Learner", "Complete 50 lessons", 1000, true, "Completed"));
        milestones.add(new Milestone("Expert Student", "Complete 100 lessons", 1500, false, "In Progress"));
        milestones.add(new Milestone("Master Learner", "Complete 200 lessons", 2000, false, "Locked"));
        milestones.add(new Milestone("Learning Legend", "Complete 500 lessons", 3000, false, "Locked"));
        milestones.add(new Milestone("Knowledge Seeker", "Score 90%+ on 10 quizzes", 1200, false, "Locked"));
        milestones.add(new Milestone("Quiz Master", "Score 100% on 5 quizzes", 800, false, "Locked"));
        
        return milestones;
    }
}
