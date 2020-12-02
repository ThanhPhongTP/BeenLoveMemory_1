package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;


public class FragmentNumLock1 extends Fragment {

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private final static String TAG = FragmentNumLock1.class.getSimpleName();
    private final static String TRUE_CODE = "1234";
    SharedPreferences sharedPreferences;
    public final static String PIN = "PINCODE";

//    public static Fragment newInstance() {
//        FragmentLock1 fragmentLock1 = new FragmentLock1();
//        return fragmentLock1;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_lock1, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        setControl(view);
        enterPIN();

        return view;
    }

    private void enterPIN() {
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLength(4);
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.d(TAG, "lock code: " + pin);
                //User input true code
                if(pin.length() == 4){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PIN, pin);
                    editor.commit();
                    replaceFragment();
                }
            }

            @Override
            public void onEmpty() {
                Log.d(TAG, "lock code is empty!");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        });
    }



    private void replaceFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.vpLock, new FragmentNumLock2());
//      fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setControl(View view) {
        mPinLockView = view.findViewById(R.id.pin_lock_view);
        mIndicatorDots = view.findViewById(R.id.indicator_dots);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }

}