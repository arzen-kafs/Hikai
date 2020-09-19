package com.example.hikai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hikai.config.SharedPrefManager;
import com.example.hikai.hikaiplayer.Player;

public class SplashActivity extends AppCompatActivity {
    private static int Splash_screen = 3200;

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView txtDesc,txtHikai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //Making the activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        txtHikai=findViewById(R.id.txtHikai);
        image=findViewById(R.id.logo);
        txtDesc=findViewById(R.id.txtDesc);


        txtHikai.setAnimation(topAnim);
        image.setAnimation(topAnim);
        txtDesc.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
           @Override
            public void run() {
              login();
                }
            },Splash_screen);

    }
    public void login(){
       // if(SharedPrefManager.getInstance(this).isLoggedIn()){
          //  Intent intent=new Intent(SplashActivity.this, MainActivity.class);
            //startActivity(intent);
            //finish();
       // }
        //else{
            Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

//}