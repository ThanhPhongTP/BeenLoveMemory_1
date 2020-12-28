package com.example.beenlovememory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.beenlovememory.Adapter.FragmentAdapter;

public class LockSettingActivity extends AppCompatActivity {

    ViewPager viewPager;
    FrameLayout frameLayout;
    TextView tvOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        setControl();
//        addTabs();
        setEvent();
        initFragmentNumLock();
    }

    private void setEvent(){
        tvOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOptions();
            }
        });
    }

    private void showDialogOptions() {
        final String[] items = {getString(R.string.pin), getString(R.string.pattern)};
        AlertDialog.Builder b = new AlertDialog.Builder(this);
//        b.setTitle("");
        b.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        initFragmentNumLock();
                        break;
                    case 1:
                        initFragmentpatternLock();
                        break;
                }
            }
        });
        b.show();
    }

    private void addTabs() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.add(new FragmentNumLock1(), "");
//        fragmentAdapter.add(new FragmentLock2(), "");
        viewPager.setAdapter(fragmentAdapter);
    }

    private void initFragmentNumLock(){
        FragmentNumLock1 fragmentNumLock1 = new FragmentNumLock1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.vpLock, fragmentNumLock1);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void initFragmentpatternLock(){
        FragmentPatternLock1 fragmentPatternLock1 = new FragmentPatternLock1();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.vpLock, fragmentPatternLock1);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void setControl() {
        frameLayout = findViewById(R.id.vpLock);
        tvOptions = findViewById(R.id.tv_option);
    }

}