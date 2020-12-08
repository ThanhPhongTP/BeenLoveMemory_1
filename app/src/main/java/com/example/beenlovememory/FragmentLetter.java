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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.threeten.bp.temporal.ChronoUnit;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import static com.example.beenlovememory.FragmentBackground.sNameFemale;
import static com.example.beenlovememory.FragmentBackground.sNameMale;
import static com.example.beenlovememory.FragmentMain.sDayStart;
import static com.example.beenlovememory.FragmentMain.sMontStart;
import static com.example.beenlovememory.FragmentMain.sYearStart;
import static com.example.beenlovememory.FragmentSetting.FONT;

public class FragmentLetter extends Fragment {

    public static TextView tvNick1, tvNick2, tvDes, tvBLM, tvMaiYeu, tvGui;
    SharedPreferences sharedPreferences;
    private Typeface tf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter, null);
        setControl(view);
        runTime();
        getName();
        getFontFromSetting();
        return view;
    }

    private void getFontFromSetting() {
        if (sharedPreferences.contains(FONT)) {
            String sFont = sharedPreferences.getString(FONT, "fonts/Xoxoxa.ttf");
            tf = Typeface.createFromAsset(getActivity().getAssets(), sFont);
            tvNick1.setTypeface(tf);
            tvNick2.setTypeface(tf);
            tvDes.setTypeface(tf);
            tvBLM.setTypeface(tf);
            tvMaiYeu.setTypeface(tf);
            tvGui.setTypeface(tf);
        }
    }

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
                    long runDays = 0, runMonths = 0, runYears = 0, Months = 0;
                    if (Build.VERSION.SDK_INT >= 26) {
                        runDays = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getDays();
                        runMonths = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getMonths();
                        runYears = Period.between(LocalDate.of(yearStart, monthStart, dayStart), LocalDate.of(y, M, d)).getYears();
                    } else {
                        runDays = ChronoUnit.DAYS.between(org.threeten.bp.LocalDate.of(yearStart, monthStart, dayStart), org.threeten.bp.LocalDate.of(y, M, d));
                        Months = ChronoUnit.MONTHS.between(org.threeten.bp.LocalDate.of(yearStart, monthStart, dayStart), org.threeten.bp.LocalDate.of(y, M, d));
                        runYears = ChronoUnit.YEARS.between(org.threeten.bp.LocalDate.of(yearStart, monthStart, dayStart), org.threeten.bp.LocalDate.of(y, M, d));
                        runMonths = Months % 12;
                    }
                    tvDes.setText("Chúng mình đã yêu nhau được " + runYears + " năm, " + runMonths + " tháng, "
                            + runDays + " ngày, " + h + " giờ, " + m + " phút, " + s + " giây.");
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void setTimeDefault() {
        tvDes.setText(R.string.chungminh);
    }

    private void getName() {
        if (sharedPreferences.contains(sNameMale)) {
            String sM = sharedPreferences.getString(sNameMale, "Nickname 1");
            tvNick1.setText(" " + sM);
        }
        if (sharedPreferences.contains(sNameFemale)) {
            String sM = sharedPreferences.getString(sNameFemale, "Nickname 2");
            tvNick2.setText(" " + sM);
        }
    }

    private void setControl(View view) {
        tvDes = view.findViewById(R.id.tvDes);
        tvNick1 = view.findViewById(R.id.nn1);
        tvNick2 = view.findViewById(R.id.nn2);
        tvBLM = view.findViewById(R.id.blm);
        tvMaiYeu = view.findViewById(R.id.my);
        tvGui = view.findViewById(R.id.gui);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}