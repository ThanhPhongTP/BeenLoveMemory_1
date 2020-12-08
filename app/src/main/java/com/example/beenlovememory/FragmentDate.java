package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
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
import android.widget.Toast;


import java.time.LocalDate;
//import org.threeten.bp.LocalDateTime;


import org.threeten.bp.Instant;
//import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.ChronoUnit;

import java.time.Period;
import java.util.Calendar;

import static com.example.beenlovememory.FragmentMain.SHAREDATE;
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

//        String valid_until = "1/1/1990";
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Date strDate = null;
//        try {
//            strDate = sdf.parse(valid_until);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (new Date().after(strDate)) {
//
//        }

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

                    long runDays, runMonths = 0, runYears, Months = 0;
                    if (Build.VERSION.SDK_INT >= 26){
                        runDays = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getDays();
                     runMonths = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getMonths();
                     runYears = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getYears();
                    }
                    else {
                        runDays = ChronoUnit.DAYS.between( org.threeten.bp.LocalDate.of(yearStart, monthStart, dayStart),  org.threeten.bp.LocalDate.of(y, M, d));
                         Months =  ChronoUnit.MONTHS.between( org.threeten.bp.LocalDate.of(yearStart, monthStart, dayStart),  org.threeten.bp.LocalDate.of(y, M, d));
                         runYears = ChronoUnit.YEARS.between( org.threeten.bp.LocalDate.of(yearStart, monthStart, dayStart),  org.threeten.bp.LocalDate.of(y, M, d));
                    }

//                    long days = 0;
//                    int runMonths = 0;
//                    DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                    Date date1 = null;
//                    Date date2 = null;
//
//                    try {
//                        date1 = simpleDateFormat.parse(d + "-" + M + "-" + y);
//                        date2 = simpleDateFormat.parse(dayStart + "-" + monthStart + "-" + yearStart);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//                    days = date1.getTime() - date2.getTime();
//
//                    long runDays = TimeUnit.MILLISECONDS.toDays(days);
//
//                    if (M > monthStart)
//                        runMonths = M - monthStart;
//                    else
//                        runMonths = monthStart - M;
//
//                    int runYears = y - yearStart;

                    long runWeek = runDays / 7 % 4;
                    long nDayle = runDays % 7;
                    long runMonths1 = Months % 12;

                    tvDay1.setText(nDayle + "");
                    if (Build.VERSION.SDK_INT >= 26)
                        tvMonth.setText(runMonths + "");
                    else 
                        tvMonth.setText(runMonths1 + "");
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
