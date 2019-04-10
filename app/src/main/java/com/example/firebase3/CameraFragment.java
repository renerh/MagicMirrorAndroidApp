package com.example.firebase3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class CameraFragment extends Fragment {

    private WebView webView;
    Button button;

    public static CameraFragment newInstance(){
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera , container, false);

        webView = (WebView) view.findViewById(R.id.web_view);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl("https://www.youtube.com/channel/UCz0XbE7RVIlIPi2HPMtCUbw/live");
            }
        });
       // WebSettings webSettings = webView.getSettings();
       // webSettings.setJavaScriptEnabled(true);
       // webView.loadUrl("https://www.youtube.com/channel/UCz0XbE7RVIlIPi2HPMtCUbw/live");


        return view;
    }






}
