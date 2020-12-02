package com.example.beenlovememory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;
import java.util.regex.Pattern;

import io.paperdb.Paper;

public class FragmentPatternLock1 extends Fragment implements PatternLockViewListener {

    PatternLockView patternLockView;
    String key = "PatternKey";
    String sPattern = "";

    SharedPreferences sharedPreferences;
    public static final String KEYPARTERN = "KEYPARTERN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_lock1, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        setControl(view);
        patternLockView.addPatternLockListener(this);
//        setEvent();
        return view;
    }

    @Override
    public void onStarted() {
    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {
    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        if (PatternLockUtils.patternToString(patternLockView, pattern).length() >= 4) {
            Log.d("knkn", PatternLockUtils.patternToString(patternLockView, pattern));
            replaceFragment();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEYPARTERN, PatternLockUtils.patternToString(patternLockView, pattern));
            editor.commit();
        } else
            Toast.makeText(getContext(), "Chọn ít nhất 4 điểm", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCleared() {
    }

    private void replaceFragment() {
        FragmentPatternLock2 fragmentPatternLock2 = new FragmentPatternLock2();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.vpLock, fragmentPatternLock2);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setControl(View view) {
        patternLockView = view.findViewById(R.id.patternLock);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}
