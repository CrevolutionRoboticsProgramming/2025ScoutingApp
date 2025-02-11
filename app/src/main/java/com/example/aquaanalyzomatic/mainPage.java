package com.example.aquaanalyzomatic;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class mainPage extends AppCompatActivity {

    private Button submitBtn, signOutBtn, autonFieldBtn;
    private EditText matchNum, teamNum, L1Num, L2Num, L3Num, L4Num, netAttemptsNum, netScoredNum, humanPlayerAttemptsNum, humanPlayerScoredNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Retrieve the data passed from SignInScreen
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");


    }
}