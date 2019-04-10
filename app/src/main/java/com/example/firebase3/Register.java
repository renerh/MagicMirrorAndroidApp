package com.example.firebase3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;


import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Register</h1>
 * The register activity will ask the user input for username,
 * email, password, and a confirmation for the password to match them.
 * it will also not let you register if you do not have all entries
 * filled, and register the new user.
 *
 * @author  Rene Hermosillo
 * @since   04-06-2019
 */

public class Register extends AppCompatActivity {

    private Button mRegistration;
    private EditText mEmail, mPassword, mName, c_Password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    Intent intent = new Intent(getApplication(), Main2Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();

        mRegistration = findViewById(R.id.register);
        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        c_Password = findViewById(R.id.c_password);


        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String cPassword = c_Password.getText().toString().trim();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill all entries", Toast.LENGTH_SHORT).show();

                }else{
                    if(password.equals(cPassword)){
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete (@NonNull Task < AuthResult > task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplication(), "Sign in ERROR. Password has to be 6 or more characters long", Toast.LENGTH_SHORT).show();
                                } else {
                                    String userId = mAuth.getCurrentUser().getUid();
                                    DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                                    Map userInfo = new HashMap<>();
                                    userInfo.put("email", email);
                                    userInfo.put("name", name);
                                    userInfo.put("profileImageUrl", "default");

                                    currentUserDb.updateChildren(userInfo);
                                }
                            }

                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match. please try again.", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
