package com.example.aquaanalyzomatic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mainPage extends AppCompatActivity {

    private FirebaseUser currentUser;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference data;
    private Button submitBtn, signOutBtn, autonLayoutButton, backToTeleBtn;
    private EditText teamNum, matchNum, teleL1Num, teleL2Num, teleL3Num, teleL4Num, autonL1Num, autonL2Num, autonL3Num, autonL4Num,
            autonNetAttemptsNum, autonNetScoredNum, teleNetAttemptsNum, teleNetScoredNum, teleProcessedNum, autonProcessedNum, HPScoredNum, HPShotsNum, username;

    private ImageView teleMinusL1, teleMinusL2, teleMinusL3, teleMinusL4, telePlusL1, telePlusL2, telePlusL3, telePlusL4, teleNetAttemptsMinus, teleNetAttemptsPlus, teleNetScoredMinus,
            teleNetScoredPlus, humanPlayerAttemptsMinus, humanPlayerAttemptsPlus, humanPlayerScoredMinus, humanPlayerScoredPlus, teleProcessedMinus, teleProcessedPlus,
            autonMinusL1, autonMinusL2, autonMinusL3, autonMinusL4, autonPlusL1, autonPlusL2, autonPlusL3, autonPlusL4,
            netAttemptsMinus, netAttemptsPlus, netScoredMinus, netScoredPlus, autonProcessedPlus, autonProcessedMinus;
    private CheckBox CheckHumanPlayer, CheckParking, CheckShallowClimb, CheckDeepClimb, CheckLeaveStart;
    private FrameLayout autonLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Retrieve the data passed from SignInScreen
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference data = firebase.getReference("matchData");


        // -------------------- Auton Vars -------------------- //
        autonMinusL1 = findViewById(R.id.AutonMinusL1);
        autonMinusL2 = findViewById(R.id.AutonMinusL2);
        autonMinusL3 = findViewById(R.id.AutonMinusL3);
        autonMinusL4 = findViewById(R.id.AutonMinusL4);
        autonPlusL1 = findViewById(R.id.AutonPlusL1);
        autonPlusL2 = findViewById(R.id.AutonPlusL2);
        autonPlusL3 = findViewById(R.id.AutonPlusL3);
        autonPlusL4 = findViewById(R.id.AutonPlusL4);
        netAttemptsMinus = findViewById(R.id.netAttemptsMinus);
        netAttemptsPlus = findViewById(R.id.netAttemptsPlus);
        netScoredMinus = findViewById(R.id.netScoredMinus);
        netScoredPlus = findViewById(R.id.netScoredPlus);
        autonProcessedPlus = findViewById(R.id.processedMinus);
        autonProcessedMinus = findViewById(R.id.processedPlus);
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

        teleProcessedPlus = findViewById(R.id.TeleProcessedPlus);
        teleProcessedMinus = findViewById(R.id.TeleProcessedMinus);

        CheckHumanPlayer = findViewById(R.id.CheckHumanPlayer);
        CheckDeepClimb = findViewById(R.id.CheckDeepClimb);
        CheckParking = findViewById(R.id.CheckParking);
        CheckShallowClimb = findViewById(R.id.CheckShallowClimb);


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
            }
        });
        humanPlayerAttemptsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPShotsNum, "up");
            }
        });
        humanPlayerScoredMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPScoredNum, "down");
            }
        });
        humanPlayerScoredPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(HPScoredNum, "up");
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

    }

    public void autonPage(Boolean Switch){
        if (Switch){
            autonLayout.setVisibility(View.VISIBLE);
            submitBtn.setEnabled(false);
            signOutBtn.setEnabled(false);
            autonLayoutButton.setVisibility(View.GONE);
            backToTeleBtn.setVisibility(View.VISIBLE);
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
            CheckHumanPlayer.setEnabled(false);
            CheckParking.setEnabled(false);
            CheckShallowClimb.setEnabled(false);
            CheckDeepClimb.setEnabled(false);
        } else {
            autonLayout.setVisibility(View.GONE);
            submitBtn.setEnabled(true);
            signOutBtn.setEnabled(true);
            autonLayoutButton.setVisibility(View.VISIBLE);
            backToTeleBtn.setVisibility(View.GONE);
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
            CheckHumanPlayer.setEnabled(true);
            CheckParking.setEnabled(true);
            CheckShallowClimb.setEnabled(true);
            CheckDeepClimb.setEnabled(true);
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
}

