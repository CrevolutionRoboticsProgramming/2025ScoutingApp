package com.example.aquaanalyzomatic;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mainPage extends AppCompatActivity {

    private Button submitBtn, signOutBtn, autonFieldButton;
    private EditText teamNum;
    private EditText TeleNumL1;
    private EditText TeleNumL2;
    private EditText TeleNumL3;
    private EditText TeleNumL4;
    private EditText AutonNumL4;
    private EditText AutonNumL3;
    private EditText AutonNumL2;
    private EditText AutonNumL1;
    private EditText netAttemptsNum;
    private EditText netScoredNum;
    private EditText TeleNetAttemptsNum;
    private EditText TeleNetScoredNum;
    private EditText HPShotsNum;
    private EditText HPScoredNum;

    private ImageView TeleMinusL1, TeleMinusL2, TeleMinusL3, TeleMinusL4, TelePlusL1, TelePlusL2, TelePlusL3, TelePlusL4, TeleNetAttemptsMinus, TeleNetAttemptsPlus, TeleNetScoredMinus,
            TeleNetScoredPlus, HumanPlayerAttemptsMinus, HumanPlayerAttemptsPlus, HumanPlayerScoredMinus, HumanPlayerScoredPlus, TeleProcessedMinus, TeleProcessedPlus,
            AutonMinusL1, AutonMinusL2, AutonMinusL3, AutonMinusL4, AutonPlusL1, AutonPlusL2, AutonPlusL3, AutonPLusL4, netAttemptsMinus, netAttemptsPlus, netScoredMinus, netScoredPlus, processedMinus, processedPlus;
    private CheckBox CheckHumanPlayer, CheckParking, CheckShallowClimb, CheckDeepClimb, CheckLeaveStart;



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
        View autonMinusL1 = findViewById(R.id.AutonMinusL1);
        View autonMinusL2 = findViewById(R.id.AutonMinusL2);
        View autonMinusL3 = findViewById(R.id.AutonMinusL3);
        View autonMinusL4 = findViewById(R.id.AutonMinusL4);
        View autonPlusL1 = findViewById(R.id.AutonPlusL1);
        View autonPlusL2 = findViewById(R.id.AutonPlusL3);
        View autonPlusL3 = findViewById(R.id.AutonPlusL3);
        View autonPlusL4 = findViewById(R.id.AutonPlusL4);
        View netAttemptsMinus = findViewById(R.id.netAttemptsMinus);
        View netAttemptsPlus = findViewById(R.id.netAttemptsPlus);
        View netScoredMinus = findViewById(R.id.netScoredMinus);
        View netScoredPlus = findViewById(R.id.netScoredPlus);
        View autonProcessedPlus = findViewById(R.id.processedMinus);
        View autonProcessedMinus = findViewById(R.id.processedPlus);

        // -------------------- TeleOp Vars -------------------- //
        View teleMinusL1 = findViewById(R.id.TeleMinusL1);
        View teleMinusL2 = findViewById(R.id.TeleMinusL2);
        View teleMinusL3 = findViewById(R.id.TeleMinusL3);
        View teleMinusL4 = findViewById(R.id.TeleMinusL4);
        View telePlusL1 = findViewById(R.id.TelePlusL1);
        View telePlusL2 = findViewById(R.id.TelePlusL2);
        View telePlusL3 = findViewById(R.id.TelePlusL3);
        View telePlusL4 = findViewById(R.id.TelePlusL4);

        View teleNetAttemptsMinus = findViewById(R.id.TeleNetAttemptsMinus);
        View teleNetAttemptsPlus = findViewById(R.id.TeleNetAttemptsPlus);
        View teleNetScoredMinus = findViewById(R.id.TeleNetScoredMinus);
        View teleNetScoredPlus = findViewById(R.id.TeleNetScoredPlus);

        View humanPlayerAttemptsMinus = findViewById(R.id.HumanPlayerAttemptsMinus);
        View humanPlayerAttemptsPlus = findViewById(R.id.HumanPlayerAttemptsPlus);
        View humanPlayerScoredMinus = findViewById(R.id.HumanPlayerScoredMinus);
        View humanPlayerScoredPlus = findViewById(R.id.HumanPlayerScoredPlus);

        View teleProcessedPlus = findViewById(R.id.TeleProcessedPlus);
        View teleProcessedMinus = findViewById(R.id.TeleProcessedMinus);



        // -------------------- TeleOp Vars -------------------- //



        // -------------------- TeleOp Vars -------------------- //



        // -------------------- Match Data Vars -------------------- //
        EditText matchNum = findViewById(R.id.MatchNum);
        EditText teamNum = findViewById(R.id.TeamNum);

        EditText autonL1Num = findViewById(R.id.AutonNumL1);
        EditText autonL2Num = findViewById(R.id.AutonNumL2);
        EditText autonL3Num = findViewById(R.id.AutonNumL3);
        EditText autonL4Num = findViewById(R.id.AutonNumL4);

        EditText teleL1Num = findViewById(R.id.TeleNumL1);
        EditText teleL2Num = findViewById(R.id.TeleNumL2);
        EditText teleL3Num = findViewById(R.id.TeleNumL3);
        EditText teleL4Num = findViewById(R.id.TeleNumL4);


        EditText autonNetAttempts = findViewById(R.id.netAttemptsNum);
        EditText autonNetScored = findViewById(R.id.netScoredNum);

        EditText teleNetAttempts = findViewById(R.id.TeleNetAttemptsNum);
        EditText teleNetScored = findViewById(R.id.TeleNetScoredNum);

        EditText teleProcessedNum = findViewById(R.id.TeleProcessedNum);
        EditText autonProcessedNum = findViewById(R.id.processedNum);


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
                changeNum(teleNetAttempts, "down");
                possibilityCheck(teleNetAttempts, teleNetScored);
            }
        });
        teleNetAttemptsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetAttempts, "up");
                possibilityCheck(teleNetAttempts, teleNetScored);
            }
        });

        teleNetScoredMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetScored, "down");
                possibilityCheck(teleNetAttempts, teleNetScored);
            }
        });
        teleNetScoredPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(teleNetScored, "up");
                possibilityCheck(teleNetAttempts, teleNetScored);
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
                changeNum(autonNetAttempts, "down");
                possibilityCheck(autonNetAttempts, autonNetScored);
            }
        });
        netAttemptsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetAttempts, "up");
                possibilityCheck(teleNetAttempts, teleNetScored);
            }
        });

        netScoredMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetScored, "down");
                possibilityCheck(autonNetAttempts, autonNetScored);
            }
        });
        netScoredPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNum(autonNetScored, "up");
                possibilityCheck(autonNetAttempts, autonNetScored);
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

