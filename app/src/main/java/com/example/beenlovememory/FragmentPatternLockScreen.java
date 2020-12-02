package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import static com.example.beenlovememory.FragmentPatternLock1.KEYPARTERN;
import static com.example.beenlovememory.FragmentPatternLock2.PATERN;

public class FragmentPatternLockScreen extends Fragment implements PatternLockViewListener {
    PatternLockView patternLockView;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_lock_screen, container, false);
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
        String sKey = sharedPreferences.getString(PATERN, "0125");
        if (PatternLockUtils.patternToString(patternLockView, pattern).equals(sKey)) {
            patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
//            Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), Main.class));
            getActivity().finish();
        }
        else
            Toast.makeText(getContext(), "Sai mật khẩu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCleared() {

    }

    private void setControl(View view) {
        patternLockView = view.findViewById(R.id.pattern);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}