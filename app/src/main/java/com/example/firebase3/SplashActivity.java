package com.example.firebase3;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
/**
 * <h1>Splash Activity</h1>
 * This activity will figure out if a user is logged in,
 * in that case it will not go to the login activity but
 * it will go to the status Fragment, Otherwise, it will
 * ask you to login
 *
 * @author  Rene Hermosillo
 * @since   04-06-2019
 */

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    public static boolean started = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplication(), Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;

        }else{
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return;
        }
    }
}
