package com.example.aquaanalyzomatic;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class mainPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Retrieve the data passed from SignInScreen
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // Use the username to customize the UI or perform actions
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText("Welcome, " + username);
    }
}