package com.example.aquaanalyzomatic;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUser extends Activity {

    FirebaseDatabase firebase;
    DatabaseReference userDatabase;
    private FirebaseAuth auth;
    private Button create, back;

    private ProgressBar progressBar;

    private EditText createUsernameEDT, passwordEDT, passwordConfirmation, fullNameEDT;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setConte      // Retrieve the data passed from SignInScreen
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");ntView(R.layout.activity_create_user);
        firebase = FirebaseDatabase.getInstance();
        userDatabase = firebase.getReference("/users");
        auth = FirebaseAuth.getInstance();

        createUsernameEDT = (EditText) findViewById(R.id.usernameEDT);
        passwordEDT = (EditText) findViewById(R.id.createUserPasswordEDT);
        passwordConfirmation = (EditText) findViewById(R.id.createUserPasswordConfirmationEDT);
        fullNameEDT = findViewById(R.id.fullName);

        create = findViewById(R.id.createUserBTN);
        back = findViewById(R.id.returnToSigninBTN);
        progressBar = findViewById(R.id.progressBar);


        create.setOnClickListener(new View.OnClickListener() {
            @androidx.test.filters.SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeScreens = new Intent(CreateUser.this, SignInScreen.class);
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
        }
    }

    private void createAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email = createUsernameEDT.getText().toString().trim();
        String fullName = fullNameEDT.getText().toString().trim();
        String password = passwordEDT.getText().toString();
        String passwordConf = passwordConfirmation.getText().toString();

        // below line is for checking whether the
        // edittext fields are empty or not.
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(passwordConf)) {
            // if the text fields are empty
            // then show the below message.
            progressBar.setVisibility(View.INVISIBLE);
            createUsernameEDT.setError("You must enter something!");
            fullNameEDT.setError("You must enter something!");
            passwordEDT.setError("You must enter something!");
            passwordConfirmation.setError("You must enter something!");
        } else if (!password.equals(passwordConf)) {
            progressBar.setVisibility(View.INVISIBLE);
            passwordEDT.setError("Passwords do not match!");
            passwordConfirmation.setError("Passwords do not match!");
        }else if(passwordEDT.getText().length() < 8){
            progressBar.setVisibility(View.INVISIBLE);
            passwordEDT.setError("Password must be 8 characters long!");
            passwordConfirmation.setError("Password must be 8 characters long!");
        }
        else {

            // [START create_user_with_email]
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(fullName).build();
                            assert user != null;
                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d(TAG, "User Profile updated");
                                    }
                                }
                            });
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(CreateUser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
            // [END create_user_with_email]
        }
    }

    private void reload() {
        Intent changeScreens = new Intent(CreateUser.this, mainPage.class);
        startActivity(changeScreens);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            Intent changeScreens = new Intent(CreateUser.this, mainPage.class);
            startActivity(changeScreens);
        }
        progressBar.setVisibility(View.INVISIBLE);
    }
}