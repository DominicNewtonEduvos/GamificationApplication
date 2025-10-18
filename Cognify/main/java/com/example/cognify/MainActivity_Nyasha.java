package com.example.cognify;

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

import com.google.firebase.FirebaseApp;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity_Nyasha extends AppCompatActivity {
    private EditText etUsername, etPassword;

    private Button btnLogin;
    private TextView tvCreateAccount, tvForgotPassword;

    private FirebaseAuth mAuth; // Firebase Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ---- Initialize Views ----
        etUsername      = findViewById(R.id.username);
        etPassword      = findViewById(R.id.password);
        btnLogin        = findViewById(R.id.btnLogin);
        tvForgotPassword= findViewById(R.id.forgotPassword);

        mAuth = FirebaseAuth.getInstance();

        // ---- Button Click Listeners ----
        btnLogin.setOnClickListener(v -> loginAdmin());


        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity_Nyasha.this, ResetPasswordActivity.class);
            startActivity(intent);
        });
    }

    // ---- Login Logic ----
    private void loginAdmin() {
        String email    = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // ---- Input validation ----
        if (TextUtils.isEmpty(email)) {
            etUsername.setError("Email is required");
            etUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        // ---- Firebase Sign In ----
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();

                        Toast.makeText(MainActivity_Nyasha.this,
                                "Login successful", Toast.LENGTH_SHORT).show();

                        // ---- Navigate to Admin Dashboard ----
                        Intent intent = new Intent(MainActivity_Nyasha.this, AdminDashboardActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity_Nyasha.this,
                                "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
