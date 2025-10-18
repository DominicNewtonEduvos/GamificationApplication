package com.example.cognify;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class PostGameScreen extends AppCompatActivity {
    private static String playedGame;
    private static int pointsForCurrentGame;

    private static String timePlayed;

    private AtomicBoolean isUpdating = new AtomicBoolean(false);

    private Button continueButton;

    private ProgressBar loadingProgressBar;

    FirebaseFirestore db;

    DocumentReference docRef = null;

    Long currentStreak;

    TextView pointsTextView;
    TextView timeTextView;

    List<String> finishMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_game_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pointsTextView = findViewById(R.id.Points);
        timeTextView = findViewById(R.id.Time);

        db = FirebaseFirestore.getInstance();

        finishMessages.add("That was amazing!");
        finishMessages.add("Nicely done! Keep it up!");
        finishMessages.add("You are a master learner!");
        finishMessages.add("Woah, that was cool!");
        finishMessages.add("That's some top notch work.");
        finishMessages.add("Fantastic!");
        Collections.shuffle(finishMessages);

        TextView congratsMessage = findViewById(R.id.congratulations);
        congratsMessage.setText(finishMessages.get(0));

        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        if (loadingProgressBar != null) {
            loadingProgressBar.setVisibility(View.VISIBLE);
        }

        showToUser();

        continueButton.setEnabled(false);
        continueButton.setAlpha(0.5f);

//        Handler handler = new Handler(Looper.getMainLooper());
//        Runnable runnable = new Runnable(){
//            @Override
//            public void run() {
//                if (loadingProgressBar != null) {
//                    loadingProgressBar.setVisibility(View.GONE);
//                }
//                performUpdates();
//                continueButton.setAlpha(1.0f);
//            }
//        };
//        handler.postDelayed(runnable, 2000);




        continueButton = findViewById(R.id.btnContinue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating.get()) {
                    Toast.makeText(PostGameScreen.this,
                            "Saving your progress...", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(PostGameScreen.this, GamesScreen.class);
                startActivity(intent);
            }
        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Show your alert dialog here
                new AlertDialog.Builder(PostGameScreen.this)
                        .setTitle("Leave Screen?")
                        .setMessage("Are you sure you want to exit? All progressed will be lost.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null) // If 'No' is clicked, do nothing (dialog dismissed)
                        .show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public static void setActivity(String activity){
        playedGame = activity;
    }

    public static void setTimePlayed(String timePlayedForGame){
        timePlayed = timePlayedForGame;
    }

//    public void getPointsForCurrentGame(){
//        pointsForCurrentGame = PointsTracker.pointsForCurrentGame;
//    }

//    public void calculateStreak(){
////        DocumentReference docRef = null;
//
//        switch (playedGame){
//            case "Crossword":{
//                docRef = db.collection("gamification").document("01");
//                break;
//            }
//            case "Definition Builder":{
//                docRef = db.collection("gamification").document("02");
//                break;
//            }
//            case "Matching Game":{
//                docRef = db.collection("gamification").document("03");
//                break;
//            }
//        }
//
//        if (docRef == null) {
//            return;
//        }
//
//        docRef.get().addOnCompleteListener(task -> {
//            if(task.isSuccessful()){
//                DocumentSnapshot document = task.getResult();
//                if(document.exists()){
//                    Timestamp dateLastPlayedTimestamp = document.getTimestamp("last_played");
//                    if (dateLastPlayedTimestamp != null){
//                        Date dateLastPlayed = dateLastPlayedTimestamp.toDate();
//
//                        // 1. Convert both dates to the modern `Instant` class.
//                        //    An Instant is a specific point on the UTC timeline.
//                        Instant lastPlayedInstant = dateLastPlayed.toInstant();
//                        Instant currentInstant = Instant.now();
//
//                        // 2. To accurately count "days", we need to consider time zones.
//                        //    We'll use the device's default time zone.
//                        ZoneId zoneId = ZoneId.systemDefault();
//                        ZonedDateTime lastPlayedZoned = lastPlayedInstant.atZone(zoneId);
//                        ZonedDateTime currentZoned = currentInstant.atZone(zoneId);
//
//                        // 3. Truncate both to the start of the day. This is crucial.
//                        //    It ensures that playing at 11 PM on Monday and 1 AM on Tuesday
//                        //    is correctly counted as a one-day difference.
//                        ZonedDateTime startOfLastPlayedDay = lastPlayedZoned.truncatedTo(ChronoUnit.DAYS);
//                        ZonedDateTime startOfCurrentDay = currentZoned.truncatedTo(ChronoUnit.DAYS);
//
//                        // 4. Calculate the number of full days between the two dates.
//                        long daysBetween = ChronoUnit.DAYS.between(startOfLastPlayedDay, startOfCurrentDay);
//
//                        getStreak();
//                        // 5. Check if the difference is greater than 1. This means the streak is broken.
//                        if (daysBetween > 1) {
//                            // The last played date is more than one day ago.
//                            // You would reset the user's streak here.
//                            // e.g., resetStreak();
//                            resetStreak();
//                        } else if (daysBetween == 1) {
//                            // The user played yesterday. The streak continues.
//                            // e.g., incrementStreak();
//                            extendStreak();
//                        } else if (daysBetween == 0) {
//                            // The user has already played today.
//                            // Do nothing, or update "last_played" if they got a higher score.
//                        } else {
//                            // daysBetween is negative. This would only happen if the
//                            // device's clock is set to a past date.
//                        }
//                    }
//                }
////                dateLastPlayed = task.getResult().getDate("last_played");
//            }
//        });
//    }

//    public void getStreak(){
//        docRef.get().addOnCompleteListener(task -> {
//            if(task.isSuccessful()){
//                DocumentSnapshot document = task.getResult();
//                if (document.exists()){
//                    currentStreak = document.getLong("streak");
//                }
//            }
//        });
//    }
//
//    public void resetStreak(){
//        currentStreak = Long.valueOf(0);
//
//        docRef.update("streak", currentStreak);
//    }
//
//    public void extendStreak(){
//        currentStreak++;
//
//        docRef.update("streak", currentStreak);
//    }

    private void performUpdates(){
        // Set the document reference based on the game played
        switch (playedGame){
            case "Crossword":
                docRef = db.collection("gamification").document("01");
                break;
            case "Definition Builder":
                docRef = db.collection("gamification").document("02");
                break;
            case "Matching Game":
                docRef = db.collection("gamification").document("03");
                break;
        }

        if (docRef == null) {
            Toast.makeText(this, "Error: Invalid game type", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable the continue button while updating
        isUpdating.set(true);
        continueButton.setEnabled(false);
        continueButton.setText("Saving...");

        // Perform all updates in a coordinated manner
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    processUpdates(document);
                    Handler handler = new Handler(Looper.getMainLooper());
                    Runnable runnable = new Runnable(){
                        @Override
                        public void run() {
                            if (loadingProgressBar != null) {
                                loadingProgressBar.setVisibility(View.GONE);
                            }
//                performUpdates();
                            continueButton.setAlpha(1.0f);
                        }
                    };
                    handler.postDelayed(runnable, 2000);
                } else {
                    handleUpdateError("Game data not found");
                }
            } else {
                handleUpdateError("Failed to retrieve game data");
            }
        });
    }

    private void processUpdates(DocumentSnapshot document) {
        // Calculate streak update
        long newStreak = calculateStreakValue(document);

        // Get current points and add new points
        Long currentTotalPoints = document.getLong("total_points");
        if (currentTotalPoints == null) {
            currentTotalPoints = 0L;
        }
        long updatedTotalPoints = currentTotalPoints + pointsForCurrentGame;

        // Prepare batch update
        Map<String, Object> updates = new HashMap<>();
        updates.put("streak", newStreak);
        updates.put("total_points", updatedTotalPoints);
        updates.put("last_played", Timestamp.now());

        // Perform the update
        docRef.update(updates)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(PostGameScreen.this,
                            "Progress saved successfully!", Toast.LENGTH_SHORT).show();
                    enableContinueButton();
                })
                .addOnFailureListener(e -> {
                    handleUpdateError("Failed to save progress: " + e.getMessage());
                });
    }

    private long calculateStreakValue(DocumentSnapshot document) {
        Timestamp dateLastPlayedTimestamp = document.getTimestamp("last_played");
        Long currentStreak = document.getLong("streak");

        if (currentStreak == null) {
            currentStreak = 0L;
        }

        if (dateLastPlayedTimestamp == null) {
            // First time playing this game
            return 1L;
        }

        Date dateLastPlayed = dateLastPlayedTimestamp.toDate();

        // Convert to Instant for modern date handling
        Instant lastPlayedInstant = dateLastPlayed.toInstant();
        Instant currentInstant = Instant.now();

        // Use device's time zone
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime lastPlayedZoned = lastPlayedInstant.atZone(zoneId);
        ZonedDateTime currentZoned = currentInstant.atZone(zoneId);

        // Truncate to start of day
        ZonedDateTime startOfLastPlayedDay = lastPlayedZoned.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime startOfCurrentDay = currentZoned.truncatedTo(ChronoUnit.DAYS);

        // Calculate days between
        long daysBetween = ChronoUnit.DAYS.between(startOfLastPlayedDay, startOfCurrentDay);

        if (daysBetween > 1) {
            // Streak broken - reset to 1
            return 1L;
        } else if (daysBetween == 1) {
            // Played yesterday - extend streak
            return currentStreak + 1;
        } else {
            // Played today already - keep current streak
            return currentStreak;
        }
    }

    private void enableContinueButton() {
        isUpdating.set(false);
        continueButton.setEnabled(true);
        continueButton.setText("Continue");
    }

    private void handleUpdateError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        enableContinueButton();
    }

    private void showToUser(){
        setPoints();
        pointsTextView.setText(Integer.toString(pointsForCurrentGame));
        timeTextView.setText(timePlayed);
    }

    private void setPoints(){
        pointsForCurrentGame = PointsTracker.getPointsForCurrentGame();
    }

//    public static void setPointsFromCrosswordGame(int crosswordPoints){
//        pointsForCurrentGame = crosswordPoints;
//    }

//    public void onBackPressedDispatcher() {
//        // Prevent going back during the game
//        new AlertDialog.Builder(this)
//                .setTitle("Exit Game")
//                .setMessage("Are you sure you want to exit? Your progress will be lost.")
//                .setPositiveButton("Yes", (dialog, which) -> {
//                    super.onBackPressed();
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }

//    @Override
//    public void onBackPressed() {
//        // Prevent going back during the game
//        new AlertDialog.Builder(this)
//                .setTitle("Exit Game")
//                .setMessage("Are you sure you want to exit? Your progress will be lost.")
//                .setPositiveButton("Yes", (dialog, which) -> {
//                    stopStopwatch();
//                    super.onBackPressed();
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }
}