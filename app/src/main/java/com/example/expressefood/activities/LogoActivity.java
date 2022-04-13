package com.example.expressefood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.expressefood.R;

public class LogoActivity extends AppCompatActivity {
    ImageView logo,backgroud;
    TextView nameLogo1,nameLogo2;
    LottieAnimationView lottieAnimationView,lottieAnimationView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);

        logo=findViewById(R.id.img_logo);
        backgroud=findViewById(R.id.img_backgroud);
        nameLogo1=findViewById(R.id.txt_name);
        nameLogo2=findViewById(R.id.txt_name2);
        lottieAnimationView=findViewById(R.id.lottie_delivery);
        lottieAnimationView2=findViewById(R.id.lottie_firework);

        backgroud.animate().translationX(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationX(1400).setDuration(1000).setStartDelay(4000);
        nameLogo1.animate().translationX(1400).setDuration(1000).setStartDelay(4000);
        nameLogo2.animate().translationX(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationX(-1600).setDuration(1000).setStartDelay(4000);
        lottieAnimationView2.animate().translationX(-2000).setDuration(1000).setStartDelay(4000);
    }
}