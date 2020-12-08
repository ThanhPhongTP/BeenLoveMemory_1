package com.example.beenlovememory;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beenlovememory.Activity.MyDateSetListener;

import java.util.Calendar;

import me.itangqi.waveloadingview.WaveLoadingView;

import static com.example.beenlovememory.FragmentSetting.FONT;
import static com.example.beenlovememory.FragmentSetting.SWITCHNOTI;
import static com.example.beenlovememory.FragmentSetting.createNotification;

public class FragmentMain extends Fragment {

    public static final String sCountDay = "COUNTDAY";
    public static final String sDayStart = "DAYSTART";
    public static final String sMontStart = "MONTHSART";
    public static final String sYearStart = "YEARSTAR";
    public static final String SHAREDATE = "SHAREDATE";
    String TITLEABOVE = "TITLEABOVE";
    String TITLEBELOW = "TITLEBELOW";

    public static TextView tvDay, tvTitleAbove, tvTitleBelow;

    SharedPreferences sharedPreferences;
    public static final int HIEU_CHINH_THOI_GIAN = 60000 * 60;// mili giay 1h
    Calendar mCurrentSelectedDate;
    WaveLoadingView waveLoadingView;
    private Typeface tf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);

        setControl(view);
        ImagePickerActivity.clearCache(getContext());
        setEvent();
        updateData();
        updateCountDay();
        getFontFromSetting();
        return view;
    }

    private void setEvent() {
        tvTitleBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOptions();
            }
        });
        tvTitleAbove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOptions();
            }
        });
        tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOptions();
            }
        });
        waveLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogOptions();
            }
        });
    }

    private void getFontFromSetting(){
        if(sharedPreferences.contains(FONT)){
            String sFont = sharedPreferences.getString(FONT, "fonts/Xoxoxa.ttf");
            tf = Typeface.createFromAsset(getActivity().getAssets(), sFont);
            tvDay.setTypeface(tf);
            tvTitleAbove.setTypeface(tf);
            tvTitleBelow.setTypeface(tf);
        }
    }

    private void updateData(){
        if(sharedPreferences.contains(TITLEABOVE)){
            String stitle = sharedPreferences.getString(TITLEABOVE, "Đang yêu");
            tvTitleAbove.setText(stitle);
        }
        if(sharedPreferences.contains(TITLEBELOW)){
            String stitle = sharedPreferences.getString(TITLEBELOW, "Ngày");
            tvTitleBelow.setText(stitle);
        }
    }

    private void updateCountDay() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                Calendar calendar = Calendar.getInstance();
//                int d = calendar.get(Calendar.DAY_OF_MONTH);
//                int m = calendar.get(Calendar.MONTH) + 1;
//                int y = calendar.get(Calendar.YEAR);
                if (sharedPreferences.contains(sCountDay)) {
                    long nDay = sharedPreferences.getLong(sCountDay, 0);
                    long hieuDay = Calendar.getInstance().getTimeInMillis() - nDay + HIEU_CHINH_THOI_GIAN;

                    long countDay = hieuDay / 86400000;
                    tvDay.setText(countDay + "");
//                    Log.d("klkl", nDay + "");
                    if(countDay == 10)
                        waveLoadingView.setProgressValue(10);
                    else if (countDay <= 25)
                        waveLoadingView.setProgressValue(20);
                    else if (countDay <= 40)
                        waveLoadingView.setProgressValue(30);
                    else if (countDay <= 55)
                        waveLoadingView.setProgressValue(40);
                    else if (countDay <= 70)
                        waveLoadingView.setProgressValue(50);
                    else if (countDay <= 85)
                        waveLoadingView.setProgressValue(60);
                    else
                        waveLoadingView.setProgressValue(70);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }



    private void showDialogOptions() {
        final String[] items = {"Thay đổi ngày", "Đổi tiêu đề trên", "Đổi tiêu đề dưới", "Đổi hình nền", "Đổi hình dáng BLM", "Chụp ảnh màn hình"};
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
//        b.setTitle("");

        b.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        setChooseDay();
                        break;
                    case 1:
                        setTitleAbove();
                        break;
                    case 2:
                        setTitleBelow();
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), WallpaperActivity.class));
                        getActivity().finish();
                        break;
                    case 4:
                        break;
                    case 5:
                }
            }
        });
        b.show();
    }




    private void setTitleAbove() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.set_title_a, null);
        dialogBuilder.setView(dialogView);
        //Set control && show dialog
        final EditText edtT1 = dialogView.findViewById(R.id.edt_t1);
        Button btnHuy = dialogView.findViewById(R.id.btn_huy_t1);
        Button btnOk = dialogView.findViewById(R.id.btn_ok_t1);
        edtT1.setText(tvTitleAbove.getText().toString());
        final AlertDialog alertShowMale = dialogBuilder.create();
        alertShowMale.show();
        //Set event
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertShowMale.cancel();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtT1.getText().toString().equals("")) {
                    tvTitleAbove.setText("Đang yêu");
                    alertShowMale.cancel();
                } else {
                    tvTitleAbove.setText(edtT1.getText().toString());
                    editor.putString(TITLEABOVE, edtT1.getText().toString());
                    editor.commit();
                    alertShowMale.cancel();
                }
            }
        });
    }

    private void setTitleBelow() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.set_title_b, null);
        dialogBuilder.setView(dialogView);
        //Set control && show dialog
        final EditText edtT2 = dialogView.findViewById(R.id.edt_t2);
        Button btnHuy = dialogView.findViewById(R.id.btn_huy_t2);
        Button btnOk = dialogView.findViewById(R.id.btn_ok_t2);
        edtT2.setText(tvTitleBelow.getText().toString());
        final AlertDialog alertShowMale = dialogBuilder.create();
        alertShowMale.show();
        //Set event
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertShowMale.cancel();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtT2.getText().toString().equals("")) {
                    //    Toast.makeText(getContext(), "Nhập tên bạn trai", Toast.LENGTH_SHORT).show();
                    tvTitleBelow.setText("Ngày");
                    alertShowMale.cancel();
                } else {
                    tvTitleBelow.setText(edtT2.getText().toString());
                    editor.putString(TITLEBELOW, edtT2.getText().toString());
                    editor.commit();
                    alertShowMale.cancel();
                }
            }
        });
    }

    private void setChooseDay() {
        dialogForCalendar(new MyDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(Calendar currentSelectedDate) {
                int dayOfMonth = currentSelectedDate.get(Calendar.DAY_OF_MONTH);
                int month = currentSelectedDate.get(Calendar.MONTH) + 1;
                int year = currentSelectedDate.get(Calendar.YEAR);

                long hieuDay = Calendar.getInstance().getTimeInMillis() - mCurrentSelectedDate.getTimeInMillis() + HIEU_CHINH_THOI_GIAN;
                long countDay = hieuDay / 86400000;
                tvDay.setText(countDay + "");
//                Toast.makeText(getContext(), mCurrentSelectedDate.getTimeInMillis() + "", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(sCountDay, mCurrentSelectedDate.getTimeInMillis());
                editor.putInt(sDayStart, dayOfMonth);
                editor.putInt(sMontStart, month);
                editor.putInt(sYearStart, year);
                editor.commit();

                if (sharedPreferences.getBoolean(SWITCHNOTI, false)){
                    Toast.makeText(getContext(), "sda", Toast.LENGTH_SHORT).show();
                    createNotification();
                } else
                    return;
            }
        });
    }

    protected void dialogForCalendar(final MyDateSetListener listener) {
        if (mCurrentSelectedDate == null) {
            mCurrentSelectedDate = Calendar.getInstance();
            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
        }
        final DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int selectedmonth, int dayOfMonth) {
                        int month = selectedmonth + 1;

                        mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis()); //refresh to now

                        mCurrentSelectedDate.set(Calendar.YEAR, year);
                        mCurrentSelectedDate.set(Calendar.MONTH, selectedmonth);
                        mCurrentSelectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        //int monthDebug = Calendar.getInstance().get(Calendar.MONTH);

                        // bat truong hop set qua ngay hien tai
                        if (mCurrentSelectedDate.getTimeInMillis() > (System.currentTimeMillis() + HIEU_CHINH_THOI_GIAN)) {
                            Toast.makeText(getContext(), "Không thể chọn ngày tương lai", Toast.LENGTH_SHORT).show();
                            //refresh to now
                            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
                            return;
                        }
                        listener.onDateSet(mCurrentSelectedDate);
                    }
                }, mCurrentSelectedDate.get(Calendar.YEAR), mCurrentSelectedDate.get(Calendar.MONTH), mCurrentSelectedDate.get(Calendar.DAY_OF_MONTH));
        mDatePicker.setTitle("Chọn ngày bắt đầu");
        mDatePicker.show();
    }

    private void setControl(View view) {
        tvTitleAbove = view.findViewById(R.id.title_above);
        tvTitleBelow = view.findViewById(R.id.title_below);
        tvDay = view.findViewById(R.id.tvday);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
        waveLoadingView = view.findViewById(R.id.wave_view);
    }
}
