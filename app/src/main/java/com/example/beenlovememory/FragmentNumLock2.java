package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import com.example.beenlovememory.FragmentNumLock1;
import com.example.beenlovememory.R;

import static com.example.beenlovememory.FragmentNumLock1.PIN;
import static com.example.beenlovememory.FragmentSetting.SWMK;

public class FragmentNumLock2 extends Fragment {


    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private final static String TAG = FragmentNumLock1.class.getSimpleName();
    private final static String TRUE_CODE = "1234";
    SharedPreferences sharedPreferences;
    public static final String PINCODE = "PINNUMCODE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_lock2, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        setControl(view);
        enterPIN();

        return view;
    }

    private void enterPIN() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLength(4);
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.d(TAG, "lock code: " + pin);
                //User input true code
                String sPin = sharedPreferences.getString(PIN, "0000");
                if(pin.equals(sPin)){
                    editor.putString(PINCODE,pin);
                    editor.putBoolean(SWMK, true);
                    editor.commit();
                    Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else
                    Toast.makeText(getContext(), "Sai mã PIN", Toast.LENGTH_SHORT).show();
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

    private void setControl(View view) {
        mPinLockView = view.findViewById(R.id.pin_lock_view1);
        mIndicatorDots = view.findViewById(R.id.indicator_dots1);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}