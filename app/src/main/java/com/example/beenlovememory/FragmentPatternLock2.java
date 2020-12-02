package com.example.beenlovememory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import static com.example.beenlovememory.FragmentPatternLock1.KEYPARTERN;
import static com.example.beenlovememory.FragmentSetting.SWMK;

public class FragmentPatternLock2 extends Fragment implements PatternLockViewListener {

    PatternLockView patternLockView;
    SharedPreferences sharedPreferences;
    public static final String PATERN = "PATERN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_lock2, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        setControl(view);
        patternLockView.addPatternLockListener(this);

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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String sKey = sharedPreferences.getString(KEYPARTERN, "0125");
        Log.d("knknk", sKey);

        if (PatternLockUtils.patternToString(patternLockView, pattern).equals(sKey)) {
            editor.putString(PATERN, PatternLockUtils.patternToString(patternLockView, pattern));
            editor.putBoolean(SWMK, true);
            editor.commit();
            patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
            Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        } else {
            patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
            Toast.makeText(getContext(), "incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCleared() {

    }


    private void setControl(View view) {
        patternLockView = view.findViewById(R.id.patternLock);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}