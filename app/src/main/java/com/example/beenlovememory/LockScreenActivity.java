package com.example.beenlovememory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.beenlovememory.FragmentNumLock1;
import com.example.beenlovememory.FragmentNumLockScreen;
import com.example.beenlovememory.R;

import static com.example.beenlovememory.FragmentNumLock1.PIN;
import static com.example.beenlovememory.FragmentNumLock2.PINCODE;
import static com.example.beenlovememory.FragmentPatternLock2.PATERN;

public class LockScreenActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        getSupportActionBar().hide();
        setControl();
        setEvent();
    }

    private void setEvent(){
        if (sharedPreferences.contains(PINCODE))
            initFragmentNumLock();
        if (sharedPreferences.contains(PATERN))
            initFragmentPatternLock();
    }

    private void initFragmentNumLock(){
        FragmentNumLockScreen fragmentNumLockScreen = new FragmentNumLockScreen();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLock, fragmentNumLockScreen);
        fragmentTransaction.commit();
    }

    private void initFragmentPatternLock(){
        FragmentPatternLockScreen fragmentPatternLockScreen = new FragmentPatternLockScreen();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLock, fragmentPatternLockScreen);
        fragmentTransaction.commit();
    }


    private void setControl() {
        frameLayout = findViewById(R.id.frameLock);
        sharedPreferences = getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}