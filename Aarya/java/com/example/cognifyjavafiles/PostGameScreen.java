package com.example.cognifyjavafiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
import java.util.Date;

public class PostGameScreen extends AppCompatActivity {
    private static String playedGame;
    private static int pointsForCurrentGame;

    private static String timePlayed;

    FirebaseFirestore db;

    DocumentReference docRef = null;

    Long currentStreak;

    TextView pointsTextView;
    TextView timeTextView;

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

        showToUser();
        performUpdates();

        Button continueButton = findViewById(R.id.button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostGameScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void setActivity(String activity){
        playedGame = activity;
    }

    public static void getTimePlayed(String timePlayedForGame){
        timePlayed = timePlayedForGame;
    }

    public void getPointsForCurrentGame(){
        pointsForCurrentGame = PointsTracker.pointsForCurrentGame;
    }

    public void calculateStreak(){
//        DocumentReference docRef = null;

        switch (playedGame){
            case "Crossword":{
                docRef = db.collection("gamification").document("01");
                break;
            }
            case "Definition Builder":{
                docRef = db.collection("gamification").document("02");
                break;
            }
            case "Matching Game":{
                docRef = db.collection("gamification").document("03");
                break;
            }
        }

        if (docRef == null) {
            return;
        }

        docRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if(document.exists()){
                    Timestamp dateLastPlayedTimestamp = document.getTimestamp("last_played");
                    if (dateLastPlayedTimestamp != null){
                        Date dateLastPlayed = dateLastPlayedTimestamp.toDate();

                        // 1. Convert both dates to the modern `Instant` class.
                        //    An Instant is a specific point on the UTC timeline.
                        Instant lastPlayedInstant = dateLastPlayed.toInstant();
                        Instant currentInstant = Instant.now();

                        // 2. To accurately count "days", we need to consider time zones.
                        //    We'll use the device's default time zone.
                        ZoneId zoneId = ZoneId.systemDefault();
                        ZonedDateTime lastPlayedZoned = lastPlayedInstant.atZone(zoneId);
                        ZonedDateTime currentZoned = currentInstant.atZone(zoneId);

                        // 3. Truncate both to the start of the day. This is crucial.
                        //    It ensures that playing at 11 PM on Monday and 1 AM on Tuesday
                        //    is correctly counted as a one-day difference.
                        ZonedDateTime startOfLastPlayedDay = lastPlayedZoned.truncatedTo(ChronoUnit.DAYS);
                        ZonedDateTime startOfCurrentDay = currentZoned.truncatedTo(ChronoUnit.DAYS);

                        // 4. Calculate the number of full days between the two dates.
                        long daysBetween = ChronoUnit.DAYS.between(startOfLastPlayedDay, startOfCurrentDay);

                        getStreak();
                        // 5. Check if the difference is greater than 1. This means the streak is broken.
                        if (daysBetween > 1) {
                            // The last played date is more than one day ago.
                            // You would reset the user's streak here.
                            // e.g., resetStreak();
                            resetStreak();
                        } else if (daysBetween == 1) {
                            // The user played yesterday. The streak continues.
                            // e.g., incrementStreak();
                            extendStreak();
                        } else if (daysBetween == 0) {
                            // The user has already played today.
                            // Do nothing, or update "last_played" if they got a higher score.
                        } else {
                            // daysBetween is negative. This would only happen if the
                            // device's clock is set to a past date.
                        }
                    }
                }
//                dateLastPlayed = task.getResult().getDate("last_played");
            }
        });
    }
    public void getStreak(){
        docRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if (document.exists()){
                    currentStreak = document.getLong("streak");
                }
            }
        });
    }

    public void resetStreak(){
        currentStreak = Long.valueOf(0);

        docRef.update("streak", currentStreak);
    }

    public void extendStreak(){
        currentStreak++;

        docRef.update("streak", currentStreak);
    }

    public void updatePoints(){
        pointsForCurrentGame = PointsTracker.pointsForCurrentGame;
        final Long[] currentTotalPoints = {0L};
        docRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if (document.exists()){
                    currentTotalPoints[0] = document.getLong("total_points");
                }
            }
        });
        currentTotalPoints[0] += pointsForCurrentGame;
        docRef.update("total_points", currentTotalPoints[0]);
    }

    public void performUpdates(){
        calculateStreak();
        updatePoints();
    }

    public void showToUser(){
        pointsTextView.setText(pointsForCurrentGame);
        timeTextView.setText(timePlayed);
    }

    public static void setPointsFromCrosswordGame(int crosswordPoints){
        pointsForCurrentGame = crosswordPoints;
    }
}