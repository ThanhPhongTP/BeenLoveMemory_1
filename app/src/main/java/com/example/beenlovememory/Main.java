package com.example.beenlovememory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.beenlovememory.Adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;

public class Main extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FrameLayout frameLayout;
    SharedPreferences sharedPreferences;
    boolean doubleBackToExitPressedOnce = false;
    public static final int RESULT_FROM_CHANGBG = 4;


    private int imgResId[] = {
//            R.drawable.ic_home_black_24dp,
//            R.drawable.ic_dashboard_black_24dp,
//            R.drawable.ic_subtitles_black_24dp,
//            R.drawable.ic_person_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().hide();
        setControl();
        setBG();
        checkBG();
        getBGFromWallpaper();
        addTabs();
    }

    @SuppressLint("ResourceType")
    private void setBG() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean isNew = extras.getBoolean("IMG", false);
            if (!isNew) {
                Intent intent = getIntent();
                int nIMG = intent.getIntExtra("IMG", R.drawable.img_bg1);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Toast.makeText(this, "Đổi hình nền thành công", Toast.LENGTH_SHORT).show();
                editor.putInt("IMAGES", nIMG);
                editor.commit();
                frameLayout.setBackgroundResource(nIMG);
            } else
                return;
        }
    }

    @SuppressLint("ResourceType")
    private void checkBG() {
        //ảnh có sẵn
        if (sharedPreferences.contains("IMAGES")) {
            int nIMGS = sharedPreferences.getInt("IMAGES", R.drawable.img_bg1);
            frameLayout.setBackgroundResource(nIMGS);
        }
        //ảnh từ camera/thư viện của device
        if (sharedPreferences.contains("IMAGEBACKGROUND")) {
            String sImg = sharedPreferences.getString("IMAGEBACKGROUND","");
            Uri uri = Uri.parse(sImg + "");
            if (uri != null){
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    frameLayout.setBackground( new BitmapDrawable(getResources(), bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void addTabs() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.add(new FragmentMemory(), "");
        fragmentAdapter.add(new FragmentBackground(), "Been love memory");
        fragmentAdapter.add(new FragmentSetting(), "");
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackground(null);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_settings_24);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_receipt_24);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void getBGFromWallpaper(){
        SharedPreferences.Editor editor = sharedPreferences .edit();
        Intent intent = getIntent();
        String sBg = intent.getStringExtra("ChangeBG");
        editor.putString("IMAGEBACKGROUND", sBg + "");
        editor.commit();
        Uri uri = Uri.parse(sBg + "");
        if (uri != null){
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                frameLayout.setBackground( new BitmapDrawable(getResources(), bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setControl() {
        viewPager = findViewById(R.id.viewPager1);
        tabLayout = findViewById(R.id.tabLayout1);
        frameLayout = findViewById(R.id.fragment);
        sharedPreferences = getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}