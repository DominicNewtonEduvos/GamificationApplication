package com.example.cognify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;  // If using a DrawerLayout for the menu

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    // Declare views as class members for easy access
    private ImageView ivProfilePicture;
    private TextView tvHello;
    private TextView tvName;
    private ImageView ivBellIcon;
    private ImageView ivMenuIcon;
    private TextView tvStreakCount;
    // Add more if needed, e.g., for day circles or titles



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);  // Link to your XML layout

        // Find views by ID
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        tvHello = findViewById(R.id.tvHello);
        tvName = findViewById(R.id.tvName);
        ivBellIcon = findViewById(R.id.ivBellIcon);
        ivMenuIcon = findViewById(R.id.ivMenuIcon);
        tvStreakCount = findViewById(R.id.tvStreakCount);

        // Find game cards (assuming you add IDs in XML)
        LinearLayout llMatchingGame = findViewById(R.id.llMatchingGame);  // Add ID in XML for this
        LinearLayout llDefinitionBuilder = findViewById(R.id.llDefinitionBuilder);  // Add ID
        LinearLayout llWordCrossword = findViewById(R.id.llCrossword);  // Add ID

        LinearLayout llCourse1 = findViewById(R.id.llCourse1);  // Add ID in XML for this
        LinearLayout llCourse2 = findViewById(R.id.llCourse2);  // Add ID
        LinearLayout llCourse3 = findViewById(R.id.llCourse3);  // Add ID

        // Set dynamic data (e.g., from SharedPreferences or API)
        // For example:
        String userName = "Kimberly";  // Fetch from data source
        int streakCount = 11;  // Fetch from data source
        tvName.setText(userName);
        tvStreakCount.setText(String.valueOf(streakCount));

        // Set click listeners
        ivBellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Bell Icon click (e.g., open notifications)
                Toast.makeText(HomePage.this, "Opening Notifications", Toast.LENGTH_SHORT).show();
                // You could start a new activity or fragment here
                // Intent intent = new Intent(HomePage.this, NotificationsActivity.class);
                // startActivity(intent);
            }
        });

        ivMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Menu Icon click (e.g., open a drawer or menu)
                Toast.makeText(HomePage.this, "Opening Menu", Toast.LENGTH_SHORT).show();
                // If you have a DrawerLayout, use:
                // DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                // drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Set click listeners for game cards
        llMatchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Matching Game click (e.g., start the game)
                Toast.makeText(HomePage.this, "Starting Matching Game", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(HomePage.this, MatchingGameActivity.class);
                // startActivity(intent);
            }
        });

        llDefinitionBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Definition Builder click
                Toast.makeText(HomePage.this, "Starting Definition Builder", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(HomePage.this, DefinitionBuilderActivity.class);
                // startActivity(intent);
            }
        });

        llWordCrossword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Word Master click
                Toast.makeText(HomePage.this, "Starting Word Master", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(HomePage.this, WordMasterActivity.class);
                // startActivity(intent);
            }
        });

        // Set click listeners for course cards (they say "Coming Soon")
        llCourse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Course 1 click
                Toast.makeText(HomePage.this, "Course 1: Coming Soon", Toast.LENGTH_SHORT).show();
                // You could show a dialog or navigate if available
            }
        });

        llCourse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Course 2: Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        llCourse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Course 3: Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_home:  // Matches your menu's nav_home
//                        // Already on home page, do nothing or refresh
//                        Toast.makeText(HomePage.this, "Home Page", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_books:  // Matches your menu's nav_books
//                        // Navigate to Books Activity
////                        startActivity(new Intent(HomePage.this, BooksActivity.class));
//                        Toast.makeText(HomePage.this, "Home Page", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_profile:  // Matches your menu's nav_profile
//                        // Navigate to Profile Activity
//                        startActivity(new Intent(HomePage.this, ProfileActivity.class));
//                        return true;
//                    case R.id.nav_games:  // Matches your menu's nav_games
//                        // Navigate to Games Activity
//                        startActivity(new Intent(HomePage.this, GamesScreen.class));
//                        return true;
//                    case R.id.nav_settings:  // Matches your menu's nav_settings
//                        // Navigate to Settings Activity
//                        startActivity(new Intent(HomePage.this, HelpFeedbackActivity.class));
//                        return true;
//                    default:
//                        return false;
//                }
                if (item.getItemId() == R.id.nav_home) {
                    // Already on home page, do nothing or refresh
                    Toast.makeText(HomePage.this, "Home Page", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.nav_books) {
                    // Navigate to Books Activity
                    // startActivity(new Intent(HomePage.this, BooksActivity.class));
                    Toast.makeText(HomePage.this, "Books (Not Implemented)", Toast.LENGTH_SHORT).show(); // Example toast
                    return true;
                } else if (item.getItemId() == R.id.nav_profile) {
                    // Navigate to Profile Activity
                    startActivity(new Intent(HomePage.this, ProfileActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.nav_games) {
                    // Navigate to Games Activity
                    startActivity(new Intent(HomePage.this, GamesScreen.class));
                    return true;
                } else if (item.getItemId() == R.id.nav_settings) {
                    // Navigate to Settings Activity
                    startActivity(new Intent(HomePage.this, HelpFeedbackActivity.class));
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}