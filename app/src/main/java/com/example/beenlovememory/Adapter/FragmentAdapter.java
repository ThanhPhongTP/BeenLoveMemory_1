package com.example.beenlovememory.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.beenlovememory.FragmentMain;
import com.example.beenlovememory.FragmentMemory;
import com.example.beenlovememory.FragmentSetting;
import com.example.beenlovememory.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FragmentAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList =  new ArrayList<>();
    private final List<String> titleFr =  new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFr.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void add(Fragment fragment, String s){
        fragmentList.add(fragment);
        titleFr.add(s);
    }

    public void addFragmentInFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}