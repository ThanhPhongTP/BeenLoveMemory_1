package com.example.beenlovememory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.beenlovememory.Adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

public class Main extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FrameLayout frameLayout;
    SharedPreferences sharedPreferences;


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
        if (sharedPreferences.contains("IMAGES")) {
            int nIMGS = sharedPreferences.getInt("IMAGES", R.drawable.img_bg1);
            frameLayout.setBackgroundResource(nIMGS);
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
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_settings_black_24dp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_receipt_black_24dp);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

//    private void setEvent(){
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentAdapter adapter = new FragmentAdapter(manager, this);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        //setIconTablayout();
//    }
//
//
//    private void setIconTablayout(){
//        tabLayout.getTabAt(0).setIcon(imgResId[0]);
//        tabLayout.getTabAt(1).setIcon(imgResId[1]);
//        tabLayout.getTabAt(2).setIcon(imgResId[2]);
//        tabLayout.getTabAt(3).setIcon(imgResId[3]);
//        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
//        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
//        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
//    }

    boolean doubleBackToExitPressedOnce = false;

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

    private void setControl() {
        viewPager = findViewById(R.id.viewPager1);
        tabLayout = findViewById(R.id.tabLayout1);
        frameLayout = findViewById(R.id.fragment);
        sharedPreferences = getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}
