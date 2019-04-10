package com.example.firebase3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;

/**
 * <h1>Status Fragment!</h1>
 * The Status Fragment shows the Logout button, the camera
 * button and the update button which would give the status
 * of the lights
 *
 * @author  Rene Hermosillo
 * @since   04-06-2019
 */


public class StatusFragment extends Fragment {
    public static StatusFragment newInstance(){
        StatusFragment fragment = new StatusFragment();
        return fragment;
    }


    TextView _desk1, _red, _blue, _green;
    Button update, camera;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status , container, false);

        Button logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
            }
        });


        _desk1 = (TextView) view.findViewById(R.id.desk1);
        _red = (TextView) view.findViewById(R.id.red1);
        _blue = (TextView) view.findViewById(R.id.blue1);
        _green = (TextView) view.findViewById(R.id.green1);
        update = (Button) view.findViewById(R.id.update);
        camera = (Button) view.findViewById(R.id.camera);
        webView = (WebView) view.findViewById(R.id.web_view1);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _red.setText(global.red);
                _blue.setText(global.blue);
                _green.setText(global.green);
                _desk1.setText(global.desk);

            }
        });

        //camera.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent intent = new Intent(getActivity(), Player.class);
        //        startActivity(intent);
        //        ((Activity) getActivity()).overridePendingTransition(0,0);
        //    }
        //});

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl("https://www.youtube.com/channel/UCz0XbE7RVIlIPi2HPMtCUbw/live");
            }
        });


        return view;
    }
    /**
     * Logs out of the app, and brings you back to the start class.
     *
     */
    private void LogOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }

}
