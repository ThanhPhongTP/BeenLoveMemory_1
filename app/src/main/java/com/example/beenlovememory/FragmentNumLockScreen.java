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

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import static com.example.beenlovememory.FragmentNumLock2.PINCODE;

public class FragmentNumLockScreen extends Fragment {
    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_lock_screen, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        setControl(view);
        setEvent();
        return view;
    }

    private void setEvent(){
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLength(4);
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                String sNumKey = sharedPreferences.getString(PINCODE, "0000");
                if(pin.equals(sNumKey)){
                    startActivity(new Intent(getActivity(), Main.class));
                    getActivity().finish();
                }
                else
                    Toast.makeText(getContext(), "Sai mã PIN", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {

            }
        });
    }



    private void setControl(View view) {
        mPinLockView = view.findViewById(R.id.pin);
        mIndicatorDots = view.findViewById(R.id.indicator);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}