package com.example.games;

/*
 * @Author Nicholas Leong        EDUV4551823
 * @Author Aarya Manowah         be.2023.q4t9k6
 * @Author Nyasha Masket        BE.2023.R3M0Y0
 * @Author Sakhile Lesedi Mnisi  BE.2022.j9f3j4
 * @Author Dominic Newton       EDUV4818782
 * @Author Kimberly Sean Sibanda EDUV4818746
 *
 * Supervisor: Stacey Byrne      Stacey.byrne@eduvos.com
 * */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageButton goToMatchingGame;
    private ImageButton goToDefinitionBuilder;
    private ImageButton goToCrossword;
    private ProgressBar loadingProgressBar;
    private boolean dataLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        TermsAndDefinitions.loadDummyTsAndDs();
        goToMatchingGame = findViewById(R.id.matchingGame);
        goToDefinitionBuilder = findViewById(R.id.definitionBuilder);
        goToCrossword = findViewById(R.id.crossword);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        disableGameButtons();
        if (loadingProgressBar != null) {
            loadingProgressBar.setVisibility(View.VISIBLE);
        }

        //Loads all documents from the Firebase Database
        TermsAndDefinitions.loadTermsAndDefinitionsFromDB(new TermsAndDefinitions.FirestoreCollectionCallback() {
            @Override
            public void onCallback(List<TermsAndDefinitions> termsAndDefinitionsList) {
                Log.d("MainActivity", "Successfully loaded " + termsAndDefinitionsList.size() + " items into the local list.");
                dataLoaded = true;

                // Hide loading indicator
                if (loadingProgressBar != null) {
                    loadingProgressBar.setVisibility(View.GONE);
                }

                // Enable game buttons
                enableGameButtons();

                Toast.makeText(MainActivity.this, "Data loaded! Ready to play.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Log.e("MainActivity", "Failed to load all terms from Firestore.", e);

                // Hide loading indicator
                if (loadingProgressBar != null) {
                    loadingProgressBar.setVisibility(View.GONE);
                }

                Toast.makeText(MainActivity.this, "Error: Could not load game data. Please restart the app.", Toast.LENGTH_LONG).show();
            }
        });

        // Set up click listeners (they won't work until buttons are enabled)
        goToMatchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataLoaded) {
                    Intent intent = new Intent(MainActivity.this, MatchingGame.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please wait, loading data...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goToDefinitionBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataLoaded) {
                    Intent intent = new Intent(MainActivity.this, DefinitionBuilder.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please wait, loading data...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goToCrossword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataLoaded) {
                    Intent intent = new Intent(MainActivity.this, Crossword.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please wait, loading data...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void disableGameButtons() {
        goToMatchingGame.setEnabled(false);
        goToDefinitionBuilder.setEnabled(false);
        goToCrossword.setEnabled(false);

        // Optional: Make them visually appear disabled
        goToMatchingGame.setAlpha(0.5f);
        goToDefinitionBuilder.setAlpha(0.5f);
        goToCrossword.setAlpha(0.5f);
    }

    private void enableGameButtons() {
        goToMatchingGame.setEnabled(true);
        goToDefinitionBuilder.setEnabled(true);
        goToCrossword.setEnabled(true);

        // Restore full opacity
        goToMatchingGame.setAlpha(1.0f);
        goToDefinitionBuilder.setAlpha(1.0f);
        goToCrossword.setAlpha(1.0f);
    }
}

//        ImageButton goToMatchingGame = findViewById(R.id.matchingGame);
//        ImageButton goToDefinitionBuilder = findViewById(R.id.definitionBuilder);
//        ImageButton goToCrossword = findViewById(R.id.crossword);
//
//        TermsAndDefinitions.loadTermsAndDefinitionsFromDB(new TermsAndDefinitions.FirestoreCollectionCallback() {
//            @Override
//            public void onCallback(List<TermsAndDefinitions> termsAndDefinitionsList) {
//                // --- THIS CODE RUNS AFTER ALL 45+ ITEMS HAVE BEEN LOADED ---
//                Log.d("MatchingGame", "Successfully loaded " + termsAndDefinitionsList.size() + " items into the local list.");
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.e("MatchingGame", "Failed to load all terms from Firestore.", e);
//                // Show an error message to the user
//                Toast.makeText(MainActivity.this, "Error: Could not load game data.", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        goToMatchingGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MatchingGame.class);
//                startActivity(intent);
//            }
//        });
//
//        goToDefinitionBuilder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DefinitionBuilder.class);
//                startActivity(intent);
//            }
//        });
//
//        goToCrossword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Crossword.class);
//                startActivity(intent);
//            }
//        });