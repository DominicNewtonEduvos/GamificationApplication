package com.example.cognify;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class BadgesActivity extends AppCompatActivity {

    private GridView badgesGridView;
    private TextView totalBadgesText;
    private BadgeAdapter badgeAdapter;
    private BadgeManager badgeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);

        badgeManager = new BadgeManager();
        
        initializeViews();
        setupBadges();
    }

    private void initializeViews() {
        badgesGridView = findViewById(R.id.grid_badges);
        totalBadgesText = findViewById(R.id.tv_total_badges);
    }

    private void setupBadges() {
        List<Badge_UI> badges = createSampleBadges();
        badgeAdapter = new BadgeAdapter(this, badges);
        badgesGridView.setAdapter(badgeAdapter);

        int earnedBadges = 0;
        for (Badge_UI badge : badges) {
            if (badge.isEarned()) {
                earnedBadges++;
            }
        }
        
        // Also check XP-based badges
        int currentXP = UserProgress.getCurrentXP();
        ArrayList<Badge> xpBadges = badgeManager.getEarnedBadges(currentXP);
        
        totalBadgesText.setText("Badges Earned: " + earnedBadges + "/" + badges.size() + 
                                " | XP Badges: " + xpBadges.size() + "/6");
    }

    private List<Badge_UI> createSampleBadges() {
        List<Badge_UI> badges = new ArrayList<>();
        
        // Earned badges
        badges.add(new Badge_UI("First Steps", "Complete your first lesson", R.drawable.badge_first_steps, true));
        badges.add(new Badge_UI("Quick Learner", "Complete 5 lessons in a day", R.drawable.badge_quick_learner, true));
        badges.add(new Badge_UI("Streak Master", "Maintain a 7-day learning streak", R.drawable.badge_streak_master, true));
        badges.add(new Badge_UI("Quiz Champion", "Score 100% on 3 quizzes", R.drawable.badge_quiz_champion, true));
        
        // Unearned badges
        badges.add(new Badge_UI("Perfect Score", "Get 100% on 10 quizzes", R.drawable.badge_perfect_score, false));
        badges.add(new Badge_UI("Speed Demon", "Complete a lesson in under 5 minutes", R.drawable.badge_speed_demon, false));
        badges.add(new Badge_UI("Night Owl", "Study after 10 PM", R.drawable.badge_night_owl, false));
        badges.add(new Badge_UI("Early Bird", "Study before 6 AM", R.drawable.badge_early_bird, false));
        badges.add(new Badge_UI("Social Learner", "Share your progress 5 times", R.drawable.badge_social_learner, false));
        badges.add(new Badge_UI("Dedicated", "Study for 30 consecutive days", R.drawable.badge_dedicated, false));
        badges.add(new Badge_UI("Explorer", "Try all available games", R.drawable.badge_explorer, false));
        badges.add(new Badge_UI("Master", "Complete all courses", R.drawable.badge_master, false));
        
        return badges;
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        setupBadges();
    }
}