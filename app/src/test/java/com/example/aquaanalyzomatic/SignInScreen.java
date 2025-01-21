package com.example.aquaanalyzomatic;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInScreen extends AppCompatActivity {

    FirebaseDatabase firebase;
    FirebaseAuth auth;

    private Button signin, createAccount, offline;
    private EditText usernameedt, passwordedt;
    private String email, password;
    private ProgressBar progressBar;
    private FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        firebase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        //get rid of action bar when running the app
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        signin = findViewById(R.id.signinBTN);
        createAccount = findViewById(R.id.createAccountBTN);
        usernameedt = findViewById(R.id.usernameEDT);
        passwordedt = findViewById(R.id.passwordEDT);
        progressBar = findViewById(R.id.progressBar2);
        frameLayout = findViewById(R.id.frameLayout);
        offline = findViewById(R.id.offline);

        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeScreens = new Intent(SignInScreen.this, offlinePage.class);
                startActivity(changeScreens);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //screen will be changed to the com.example.aquaanalyzomatic.CreateUser Screen
                Intent changeScreens = new Intent(SignInScreen.this, CreateUser.class);
                startActivity(changeScreens);
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            reload();
        } else{

        }
    }

    private void signIn() {

        frameLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        email = usernameedt.getText().toString().trim();
        password = passwordedt.getText().toString();
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            frameLayout.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignInScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void reload() {
        Intent changeScreens = new Intent(SignInScreen.this, mainPage.class);
        startActivity(changeScreens);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            Intent changeScreens = new Intent(SignInScreen.this, mainPage.class);
            startActivity(changeScreens);
        }
        frameLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

}