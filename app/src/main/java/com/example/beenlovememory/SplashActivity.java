package com.example.beenlovememory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.beenlovememory.FragmentNumLock1.PIN;
import static com.example.beenlovememory.FragmentNumLock2.PINCODE;
import static com.example.beenlovememory.FragmentPatternLock1.KEYPARTERN;
import static com.example.beenlovememory.FragmentPatternLock2.PATERN;

public class SplashActivity extends AppCompatActivity {

    Animation a_logo, a_title;
    ImageView img_Logo, img_title;
    TextView tv_tile, tv_title1, tv_title2, tv_info;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        setControl();
        setAnimation();
        setEvent();


    }

    private void setEvent(){
        Thread bamgio = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (Exception e) {
                } finally {
                    if(sharedPreferences.contains(PINCODE) || sharedPreferences.contains(PATERN)){
                        startActivity(new Intent(getApplication(), LockScreenActivity.class));
                        finish();
                    }
                   else if (!sharedPreferences.contains(PINCODE) && !sharedPreferences.contains(PATERN)){
                        startActivity(new Intent(getApplication(), Main.class));
                        finish();
                    }
                }
            }
        };
        bamgio.start();
    }

    private void setAnimation() {
        a_logo = AnimationUtils.loadAnimation(this, R.anim.animation_logo);
        a_title = AnimationUtils.loadAnimation(this, R.anim.animation_title);
        img_Logo.setAnimation(a_logo);
        img_title.setAnimation(a_title);
        tv_tile.setAnimation(a_title);
        tv_title1.setAnimation(a_title);
        tv_title2.setAnimation(a_title);
        tv_info.setAnimation(a_title);
//        Display display = getWindowManager().getDefaultDisplay();
//        int height = display.getHeight();// lay kt man hinh
//        Animation animation = new TranslateAnimation(0,0,height,0);
//        animation.setDuration(3000);
//        animation.setFillAfter(true);
//        tv_tile.startAnimation(animation);
    }
    private void setControl() {
        img_Logo = findViewById(R.id.imglogo);
        img_title = findViewById(R.id.imgtitle);
        tv_tile = findViewById(R.id.tvtitle);
        tv_title1 = findViewById(R.id.tvtitle1);
        tv_title2 = findViewById(R.id.tvtitle2);
        tv_info = findViewById(R.id.tv_info);
        sharedPreferences = getSharedPreferences("BLM", Context.MODE_PRIVATE);

    }
}
