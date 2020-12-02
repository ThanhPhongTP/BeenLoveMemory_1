package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import static com.example.beenlovememory.FragmentMain.sDayStart;
import static com.example.beenlovememory.FragmentMain.sMontStart;
import static com.example.beenlovememory.FragmentMain.sYearStart;
import static com.example.beenlovememory.FragmentSetting.FONT;

public class FragmentDate extends Fragment {

    public static TextView tvSecond, tvMinute, tvHour, tvDay1, tvMonth, tvYear, tvWeek, tvTimeStart, tvNam, tvThang, tvTuan, tvNgay, tvGio, tvPhut, tvGiay;
    SharedPreferences sharedPreferences;
    LinearLayout linearLayout;
    private Typeface tf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        linearLayout = view.findViewById(R.id.fmDate);
        setControl(view);
        runTime();

        getFontFromSetting();

        return view;
    }

    private void getFontFromSetting() {
        if (sharedPreferences.contains(FONT)) {
            String sFont = sharedPreferences.getString(FONT, "fonts/Xoxoxa.ttf");
            tf = Typeface.createFromAsset(getActivity().getAssets(), sFont);
            tvSecond.setTypeface(tf);
            tvMinute.setTypeface(tf);
            tvHour.setTypeface(tf);
            tvDay1.setTypeface(tf);
            tvMonth.setTypeface(tf);
            tvYear.setTypeface(tf);
            tvWeek.setTypeface(tf);
            tvTimeStart.setTypeface(tf);
            tvNam.setTypeface(tf);
            tvThang.setTypeface(tf);
            tvTuan.setTypeface(tf);
            tvNgay.setTypeface(tf);
            tvGio.setTypeface(tf);
            tvPhut.setTypeface(tf);
            tvGiay.setTypeface(tf);
        }
    }

    private void setTimeDefault() {
        tvDay1.setText("0");
        tvMonth.setText("0");
        tvYear.setText("0");
        tvWeek.setText("0");
        tvHour.setText("0");
        tvMinute.setText("0");
        tvSecond.setText("0");
        tvTimeStart.setText("0/0/0");
    }

    private void updateDataFromFragment() {

    }

//    public void reciverDataFromFragment(int nDay, int nMonth, int nYear){
//        Toast.makeText(getContext(), nDay + "/" + nMonth + "/" + nYear, Toast.LENGTH_SHORT).show();
//    }

    private void runTime() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                int dayStart = sharedPreferences.getInt(sDayStart, 0);
                int monthStart = sharedPreferences.getInt(sMontStart, 0);
                int yearStart = sharedPreferences.getInt(sYearStart, 0);

                Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int M = calendar.get(Calendar.MONTH) + 1;
                int y = calendar.get(Calendar.YEAR);
                int h = calendar.get(Calendar.HOUR_OF_DAY);
                int m = calendar.get(Calendar.MINUTE);
                int s = calendar.get(Calendar.SECOND);

                if (dayStart == 0 || monthStart == 0)
                    setTimeDefault();
                else {
                    int runDays = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getDays();
                    int runMonths = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getMonths();
                    int runYears = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getYears();

                    long runWeek = runDays / 7;
                    int nDayle = runDays % 7;

//                    Log.d("abcc", runWeek + "");
//                Log.d("abca", runDays + "");
                    tvDay1.setText(nDayle + "");
                    tvMonth.setText(runMonths + "");
                    tvYear.setText(runYears + "");
                    tvHour.setText(h + "");
                    tvMinute.setText(m + "");
                    tvSecond.setText(s + "");
                    tvWeek.setText(runWeek + "");
                    tvTimeStart.setText(dayStart + "/" + monthStart + "/" + yearStart);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    private void setControl(View view) {
        tvNam = view.findViewById(R.id.tvNam);
        tvThang = view.findViewById(R.id.tvThang);
        tvTuan = view.findViewById(R.id.tvTuan);
        tvNgay = view.findViewById(R.id.tvNgay);
        tvGio = view.findViewById(R.id.tvGio);
        tvPhut = view.findViewById(R.id.tvPhut);
        tvGiay = view.findViewById(R.id.tvGiay);
        tvSecond = view.findViewById(R.id.tvsSecond);
        tvHour = view.findViewById(R.id.tvHour);
        tvMinute = view.findViewById(R.id.tvMinute);
        tvDay1 = view.findViewById(R.id.tvDay);
        tvMonth = view.findViewById(R.id.tvMonth);
        tvYear = view.findViewById(R.id.tvYear);
        tvWeek = view.findViewById(R.id.tvWeek);
        tvTimeStart = view.findViewById(R.id.tvTimeStart);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }


}
