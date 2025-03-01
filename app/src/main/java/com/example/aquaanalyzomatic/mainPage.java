package com.example.aquaanalyzomatic;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class mainPage extends AppCompatActivity {

    private FirebaseUser currentUser;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference data;
    private Button submitBtn, signOutBtn, autonLayoutButton, backToTeleBtn;
    private EditText teamNum, matchNum, teleL1Num, teleL2Num, teleL3Num, teleL4Num, autonL1Num, autonL2Num, autonL3Num, autonL4Num,
            autonNetAttemptsNum, autonNetScoredNum, teleNetAttemptsNum, teleNetScoredNum, teleProcessedNum, autonProcessedNum, HPScoredNum, HPShotsNum, usernameE;

    private ImageView teleMinusL1, teleMinusL2, teleMinusL3, teleMinusL4, telePlusL1, telePlusL2, telePlusL3, telePlusL4, teleNetAttemptsMinus, teleNetAttemptsPlus, teleNetScoredMinus,
            teleNetScoredPlus, humanPlayerAttemptsMinus, humanPlayerAttemptsPlus, humanPlayerScoredMinus, humanPlayerScoredPlus, teleProcessedMinus, teleProcessedPlus,
            autonMinusL1, autonMinusL2, autonMinusL3, autonMinusL4, autonPlusL1, autonPlusL2, autonPlusL3, autonPlusL4,
            netAttemptsMinus, netAttemptsPlus, netScoredMinus, netScoredPlus, autonProcessedPlus, autonProcessedMinus;
    private CheckBox checkHumanPlayer, checkParking, checkShallowClimb, checkDeepClimb, checkLeaveStart;
    private ConstraintLayout autonLayout, humanPlayerLayout;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Retrieve the data passed from SignInScreen
        //Intent intent = getIntent();
        //String username = intent.getStringExtra("username");

        database = FirebaseDatabase.getInstance();
        data = database.getReference("matchData");

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(authStateListener);

        usernameE = findViewById(R.id.Username);

        // -------------------- Auton Vars -------------------- //
        autonMinusL1 = findViewById(R.id.AutonMinusL1);
        autonMinusL2 = findViewById(R.id.AutonMinusL2);
        autonMinusL3 = findViewById(R.id.AutonMinusL3);
        autonMinusL4 = findViewById(R.id.AutonMinusL4);
        autonPlusL1 = findViewById(R.id.AutonPlusL1);
        autonPlusL2 = findViewById(R.id.AutonPlusL2);
        autonPlusL3 = findViewById(R.id.AutonPlusL3);
        autonPlusL4 = findViewById(R.id.AutonPlusL4);
        checkLeaveStart = findViewById(R.id.CheckLeaveStart);
        netAttemptsMinus = findViewById(R.id.netAttemptsMinus);
        netAttemptsPlus = findViewById(R.id.netAttemptsPlus);
        netScoredMinus = findViewById(R.id.netScoredMinus);
        netScoredPlus = findViewById(R.id.netScoredPlus);
        autonProcessedPlus = findViewById(R.id.processedPlus);
        autonProcessedMinus = findViewById(R.id.processedMinus);
        autonLayoutButton = findViewById(R.id.autonLayoutBtn);
        backToTeleBtn = findViewById(R.id.backToTele);

        // -------------------- TeleOp Vars -------------------- //
        teleMinusL1 = findViewById(R.id.TeleMinusL1);
        teleMinusL2 = findViewById(R.id.TeleMinusL2);
        teleMinusL3 = findViewById(R.id.TeleMinusL3);
        teleMinusL4 = findViewById(R.id.TeleMinusL4);
        telePlusL1 = findViewById(R.id.TelePlusL1);
        telePlusL2 = findViewById(R.id.TelePlusL2);
        telePlusL3 = findViewById(R.id.TelePlusL3);
        telePlusL4 = findViewById(R.id.TelePlusL4);

        teleNetAttemptsMinus = findViewById(R.id.TeleNetAttemptsMinus);
        teleNetAttemptsPlus = findViewById(R.id.TeleNetAttemptsPlus);
        teleNetScoredMinus = findViewById(R.id.TeleNetScoredMinus);
        teleNetScoredPlus = findViewById(R.id.TeleNetScoredPlus);

        humanPlayerAttemptsMinus = findViewById(R.id.HumanPlayerAttemptsMinus);
        humanPlayerAttemptsPlus = findViewById(R.id.HumanPlayerAttemptsPlus);
        humanPlayerScoredMinus = findViewById(R.id.HumanPlayerScoredMinus);
        humanPlayerScoredPlus = findViewById(R.id.HumanPlayerScoredPlus);
        humanPlayerLayout = findViewById(R.id.humanPlayerLayout);

        teleProcessedPlus = findViewById(R.id.TeleProcessedPlus);
        teleProcessedMinus = findViewById(R.id.TeleProcessedMinus);

        checkHumanPlayer = findViewById(R.id.CheckHumanPlayer);
        checkDeepClimb = findViewById(R.id.CheckDeepClimb);
        checkParking = findViewById(R.id.CheckParking);
        checkShallowClimb = findViewById(R.id.CheckShallowClimb);


        // -------------------- Match Data Vars -------------------- //
        matchNum = findViewById(R.id.MatchNum);
        teamNum = findViewById(R.id.TeamNum);

        autonL1Num = findViewById(R.id.AutonNumL1);
        autonL2Num = findViewById(R.id.AutonNumL2);
        autonL3Num = findViewById(R.id.AutonNumL3);
        autonL4Num = findViewById(R.id.AutonNumL4);

        teleL1Num = findViewById(R.id.TeleNumL1);
        teleL2Num = findViewById(R.id.TeleNumL2);
        teleL3Num = findViewById(R.id.TeleNumL3);
        teleL4Num = findViewById(R.id.TeleNumL4);


        autonNetAttemptsNum = findViewById(R.id.netAttemptsNum);
        autonNetScoredNum = findViewById(R.id.netScoredNum);

        teleNetAttemptsNum = findViewById(R.id.TeleNetAttemptsNum);
        teleNetScoredNum = findViewById(R.id.TeleNetScoredNum);

        teleProcessedNum = findViewById(R.id.TeleProcessedNum);
        autonProcessedNum = findViewById(R.id.processedNum);

        //Misc
        signOutBtn = findViewById(R.id.signOutBtn);
        submitBtn = findViewById(R.id.submitBtn);
        autonLayout = findViewById(R.id.autonLayout);
        HPScoredNum = findViewById(R.id.HumanPlayerNum);
        HPShotsNum = findViewById(R.id.HumanPlayerAttemptsNum);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
            }
        });


        //TELEOP CORAL BUTTONS

        teleMinusL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL1Num, "down");
            }
        });

        telePlusL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL1Num, "up");
            }
        });

        teleMinusL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL2Num, "down");
            }
        });

        telePlusL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL2Num, "up");
            }
        });

        teleMinusL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL3Num, "down");
            }
        });
        telePlusL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL3Num, "up");
            }
        });

        teleMinusL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL4Num, "down");
            }
        });
        telePlusL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleL4Num, "up");
            }
        });

//TELE ALGAE SCORING
        teleNetAttemptsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetAttemptsNum, "down");
                possibilityCheck(teleNetAttemptsNum, teleNetScoredNum);
            }
        });
        teleNetAttemptsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetAttemptsNum, "up");
                possibilityCheck(teleNetAttemptsNum, teleNetScoredNum);
            }
        });

        teleNetScoredMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetScoredNum, "down");
                possibilityCheck(teleNetAttemptsNum, teleNetScoredNum);
            }
        });
        teleNetScoredPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetScoredNum, "up");
                possibilityCheck(teleNetAttemptsNum, teleNetScoredNum);
            }
        });

        teleProcessedMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleProcessedNum, "down");

            }
        });

        teleProcessedPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleProcessedNum, "up");

            }
        });
        //TELEOP HUMAN PLAYERS

        humanPlayerAttemptsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPShotsNum, "down");
                possibilityCheck(HPScoredNum, HPShotsNum);
            }
        });
        humanPlayerAttemptsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPShotsNum, "up");
                possibilityCheck(HPShotsNum, HPScoredNum);
            }
        });
        humanPlayerScoredMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPScoredNum, "down");
                possibilityCheck(HPShotsNum, HPScoredNum);
            }
        });
        humanPlayerScoredPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPScoredNum, "up");
                possibilityCheck(HPShotsNum, HPScoredNum);
            }
        });

//AUTON CORAL

        autonMinusL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL1Num, "down");
            }
        });

        autonPlusL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL1Num, "up");
            }
        });

        autonMinusL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL2Num, "down");
            }
        });

        autonPlusL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL2Num, "up");
            }
        });

        autonMinusL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL3Num, "down");
            }
        });
        autonPlusL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL3Num, "up");
            }
        });

        autonMinusL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL4Num, "down");
            }
        });
        autonPlusL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonL4Num, "up");
            }
        });

//AUTON ALGAE SCORING

        netAttemptsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetAttemptsNum, "down");
                possibilityCheck(autonNetAttemptsNum, autonNetScoredNum);
            }
        });
        netAttemptsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetAttemptsNum, "up");
                possibilityCheck(autonNetAttemptsNum, autonNetScoredNum);
            }
        });

        netScoredMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetScoredNum, "down");
                possibilityCheck(autonNetAttemptsNum, autonNetScoredNum);
            }
        });
        netScoredPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetScoredNum, "up");
                possibilityCheck(autonNetAttemptsNum, autonNetScoredNum);
            }
        });

        autonProcessedMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonProcessedNum, "down");
            }
        });

        autonProcessedPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonProcessedNum, "up");
            }
        });

        autonLayoutButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));

        autonLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autonPage(true);
            }
        });

        backToTeleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autonPage(false);
            }
        });

        checkParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDeepClimb.setChecked(false);
                checkShallowClimb.setChecked(false);
            }
        });

        checkShallowClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkParking.setChecked(false);
                checkDeepClimb.setChecked(false);
            }
        });

        checkDeepClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkParking.setChecked(false);
                checkShallowClimb.setChecked(false);
            }
        });

        checkHumanPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHPBox();
            }
        });

    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentuser = auth.getCurrentUser();
        if(currentuser != null){
            usernameE.setText(currentuser.getDisplayName());
        }
    }
    
    public void checkHPBox(){
        if (checkHumanPlayer.isChecked()){
            humanPlayerLayout.setVisibility(View.VISIBLE);
        } else {
            humanPlayerLayout.setVisibility(View.GONE);
        }
    }
    public void autonPage(Boolean Switch){
        if (Switch){
            autonLayout.setVisibility(View.VISIBLE);
            submitBtn.setEnabled(false);
            signOutBtn.setEnabled(false);
            autonLayoutButton.setVisibility(View.GONE);
            backToTeleBtn.setVisibility(View.VISIBLE);
            humanPlayerLayout.setVisibility(View.GONE);
            //teleop buttons
            teleMinusL1.setEnabled(false);
            teleMinusL2.setEnabled(false);
            teleMinusL3.setEnabled(false);
            teleMinusL4.setEnabled(false);
            telePlusL1.setEnabled(false);
            telePlusL2.setEnabled(false);
            telePlusL3.setEnabled(false);
            telePlusL4.setEnabled(false);
            teleNetAttemptsMinus.setEnabled(false);
            teleNetAttemptsPlus.setEnabled(false);
            teleNetScoredMinus.setEnabled(false);
            teleNetScoredPlus.setEnabled(false);
            checkHumanPlayer.setEnabled(false);
            checkParking.setEnabled(false);
            checkShallowClimb.setEnabled(false);
            checkDeepClimb.setEnabled(false);
        } else {
            autonLayout.setVisibility(View.GONE);
            submitBtn.setEnabled(true);
            signOutBtn.setEnabled(true);
            autonLayoutButton.setVisibility(View.VISIBLE);
            backToTeleBtn.setVisibility(View.GONE);
            checkHPBox();
            //teleop buttons
            teleMinusL1.setEnabled(true);
            teleMinusL2.setEnabled(true);
            teleMinusL3.setEnabled(true);
            teleMinusL4.setEnabled(true);
            telePlusL1.setEnabled(true);
            telePlusL2.setEnabled(true);
            telePlusL3.setEnabled(true);
            telePlusL4.setEnabled(true);
            teleNetAttemptsMinus.setEnabled(true);
            teleNetAttemptsPlus.setEnabled(true);
            teleNetScoredMinus.setEnabled(true);
            teleNetScoredPlus.setEnabled(true);
            checkHumanPlayer.setEnabled(true);
            checkParking.setEnabled(true);
            checkShallowClimb.setEnabled(true);
            checkDeepClimb.setEnabled(true);
        }
    }

    public void submitData(){
        // Code to push data to FireBase
        if(matchNum.getText().toString().equals("")){
            matchNum.setError("You must enter a Match Number!");
            //loading(false);
        }else if(teamNum.getText().toString().equals("")) {
            teamNum.setError("You must enter a team number!");
            //loading(false);
        } else {
            String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
            int checkHumanPlayerVal = 0;
            int checkParkingVal = 0;
            int checkShallowClimbVal = 0;
            int checkDeepClimbVal = 0;
            int checkLeaveStartVal = 0;

            String key = data.child("matchData").push().getKey();

            if (checkHumanPlayer.isChecked()){
                checkHumanPlayerVal = 1;
            }
            if (checkParking.isChecked()){
                checkParkingVal = 1;
            }
            if (checkShallowClimb.isChecked()){
                checkShallowClimbVal = 1;
            }
            if (checkDeepClimb.isChecked()){
                checkDeepClimbVal = 1;
            }
            if (checkLeaveStart.isChecked()){
                checkLeaveStartVal = 1;
            }

            matchData match = new matchData(
                    auth.getUid(),
                    currentDate,
                    Integer.parseInt(teamNum.getText().toString()),
                    Integer.parseInt(matchNum.getText().toString()),
                    Integer.parseInt(teleL1Num.getText().toString()),
                    Integer.parseInt(teleL2Num.getText().toString()),
                    Integer.parseInt(teleL3Num.getText().toString()),
                    Integer.parseInt(teleL4Num.getText().toString()),
                    Integer.parseInt(autonL1Num.getText().toString()),
                    Integer.parseInt(autonL2Num.getText().toString()),
                    Integer.parseInt(autonL3Num.getText().toString()),
                    Integer.parseInt(autonL4Num.getText().toString()),
                    Integer.parseInt(autonNetAttemptsNum.getText().toString()),
                    Integer.parseInt(autonNetScoredNum.getText().toString()),
                    Integer.parseInt(teleNetAttemptsNum.getText().toString()),
                    Integer.parseInt(teleNetScoredNum.getText().toString()),
                    Integer.parseInt(teleProcessedNum.getText().toString()),
                    Integer.parseInt(autonProcessedNum.getText().toString()),
                    Integer.parseInt(HPScoredNum.getText().toString()),
                    Integer.parseInt(HPShotsNum.getText().toString()),
                    checkHumanPlayerVal,
                    checkParkingVal,
                    checkShallowClimbVal,
                    checkDeepClimbVal,
                    checkLeaveStartVal
                    );
            Map<String, Object> matchValues = match.toMap();

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/" + key, matchValues);

            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            if(isConnected) {
                data.updateChildren(childUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        reload();

                    }
                });
            }else {
                data.updateChildren(childUpdates);
                reload();
            }
        }
    }

    public void changeNum(EditText item, String direction) {
        // Method to change the numbers for the textboxes
        if (direction.equals("up")) {
            //What to do to add a number
            int i = Integer.parseInt(item.getText().toString());
            i++;
            item.setText(Integer.toString(i));
        } else if (direction.equals("down")) {
            // What to do to subtract the number
            int i = Integer.parseInt(item.getText().toString());
            i--;
            if (!(i < 0)) {
                item.setText(Integer.toString(i));
            }
        }


    }
    public void possibilityCheck(EditText shots, EditText scored){
        if(Integer.parseInt(shots.getText().toString()) < Integer.parseInt(scored.getText().toString())){
            int i = Integer.parseInt(shots.getText().toString());
            i++;
            shots.setText(Integer.toString(i));
        }
    }

    FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
        if (auth.getCurrentUser() == null){
            signOutComplete();
        }
    };
    public void signOutComplete(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            Intent changeScreen = new Intent(mainPage.this, SignInScreen.class);
            startActivity(changeScreen);
        }
    }

    public void reload(){
        Intent resetScreen = new Intent(mainPage.this, mainPage.class);
        startActivity(resetScreen);
    }
}

