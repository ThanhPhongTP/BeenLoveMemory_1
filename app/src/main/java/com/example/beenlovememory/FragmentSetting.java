package com.example.beenlovememory;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.TextView;

import com.example.beenlovememory.Adapter.ListFontAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.example.beenlovememory.Notification.App.ID;
import static com.example.beenlovememory.FragmentBackground.sNameFemale;
import static com.example.beenlovememory.FragmentBackground.sNameMale;

import static com.example.beenlovememory.FragmentMain.HIEU_CHINH_THOI_GIAN;
import static com.example.beenlovememory.FragmentMain.sCountDay;
import static com.example.beenlovememory.FragmentNumLock2.PINCODE;
import static com.example.beenlovememory.FragmentPatternLock2.PATERN;


public class FragmentSetting extends Fragment {

    private static Context context = null;

    Switch swMK, swNo;
    TextView tvFont, tvcd;

    static SharedPreferences sharedPreferences;
    public static final String FONT = "FONT";
    public static  final  String SWITCHNOTI = "NOTI";
    NotificationManagerCompat notificationManager;
    public static final String SWMK = "SWITCHMK";
    private ArrayList<Integer> mListFont;
    private Typeface tf;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        notificationManager = NotificationManagerCompat.from(getContext());
        setControl(view);
        CheckSW();
        context=getActivity(); //chuyển getActivity, getContext sang context dùng trong static(function createNotification)
        setEvent();
        return view;
    }

    private void CheckSW() {
        if (sharedPreferences.getBoolean(SWITCHNOTI, false))
            swNo.setChecked(true);
        else
            swNo.setChecked(false);
        if (sharedPreferences.getBoolean(SWMK, false))
            swMK.setChecked(true);
        else
            swMK.setChecked(false);
    }

    private void setEvent() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        swMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swMK.isChecked()) {
                    startActivity(new Intent(getActivity(), LockSettingActivity.class));
//                    editor.putBoolean("SWITCHMK", true);
//                    editor.commit();
                } else {
                    editor.remove(PINCODE);
                    editor.remove(PATERN);
                    editor.putBoolean(SWMK, false);
                    editor.commit();
                }
            }
        });

        swNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swNo.isChecked()) {
                    createNotification();
                    editor.putBoolean(SWITCHNOTI, true);
                    editor.commit();
                } else {
                    ClearNotification();
                    editor.putBoolean(SWITCHNOTI, false);
                    editor.commit();
                }
            }
        });
        tvFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialogChooseFont();
            }
        });
    }

    private void displayAlertDialogChooseFont() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.list_font, null);
        dialogBuilder.setView(dialogView);

        ListView mLv = (ListView) dialogView.findViewById(R.id.lv_font);

        mListFont = new ArrayList<>();
        mListFont.add(R.drawable.font_xoxoxa);
        mListFont.add(R.drawable.font_xanadu);
        mListFont.add(R.drawable.font_xcelsion_italic);
        mListFont.add(R.drawable.font_xefus);
        mListFont.add(R.drawable.font_xenowort);
        mListFont.add(R.drawable.font_xeroxsans);
        mListFont.add(R.drawable.font_xeroxserif);
        mListFont.add(R.drawable.font_xeroxserifitalic);
        mListFont.add(R.drawable.font_xped);
        mListFont.add(R.drawable.font_xtrusion);
        mListFont.add(R.drawable.font_xxon);
        mListFont.add(R.drawable.font_yellowjacket);
        mListFont.add(R.drawable.font_yougone);
        mListFont.add(R.drawable.font_allura);
        mListFont.add(R.drawable.font_greatvibes);
        mListFont.add(R.drawable.font_arizonia);
        mListFont.add(R.drawable.font_cac_champagne);
        mListFont.add(R.drawable.font_fff_tusj);
        mListFont.add(R.drawable.font_pacifico);

        ListFontAdapter mAdapter = new ListFontAdapter(getContext(), mListFont);
        mLv.setAdapter(mAdapter);

        final AlertDialog alertShow = dialogBuilder.create();
        alertShow.show();
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (position == 0) {
                    setFont("fonts/Xoxoxa.ttf");

                    alertShow.cancel();
                } else if (position == 1) {
                    setFont("fonts/Xanadu.ttf");
                    alertShow.cancel();
                } else if (position == 2) {
                    setFont("fonts/XcelsionItalic.ttf");
                    alertShow.cancel();
                } else if (position == 3) {
                    setFont("fonts/Xefus.ttf");
                    alertShow.cancel();
                } else if (position == 4) {
                    setFont("fonts/Xenowort.ttf");
                    alertShow.cancel();
                } else if (position == 5) {
                    setFont("fonts/XeroxSansSerifNarrowBoldOblique.ttf");
                    alertShow.cancel();
                } else if (position == 6) {
                    setFont("fonts/XeroxSerifNarrowBoldItalic.ttf");
                    alertShow.cancel();
                } else if (position == 7) {
                    setFont("fonts/XeroxSerifNarrowItalic.ttf");
                    alertShow.cancel();
                } else if (position == 8) {
                    setFont("fonts/XPEDShadow.ttf");
                    alertShow.cancel();
                } else if (position == 9) {
                    setFont("fonts/Xtrusion.ttf");
                    alertShow.cancel();
                } else if (position == 10) {
                    setFont("fonts/XXonXXoff.ttf");
                    alertShow.cancel();
                } else if (position == 11) {
                    setFont("fonts/YellowjacketItalic.ttf");
                    alertShow.cancel();
                } else if (position == 12) {
                    setFont("fonts/You'reGoneItalic.ttf");
                    alertShow.cancel();
                } else if (position == 13) {
                    setFont("fonts/AlluraRegular.ttf");
                    alertShow.cancel();
                } else if (position == 14) {
                    setFont("fonts/GreatVibesRegular.ttf");
                    alertShow.cancel();
                } else if (position == 15) {
                    setFont("fonts/Arizonia-Regular.ttf");
                    alertShow.cancel();
                } else if (position == 16) {
                    setFont("fonts/cac_champagne.ttf");
                    alertShow.cancel();
                } else if (position == 17) {
                    setFont("fonts/FFF_Tusj.ttf");
                    alertShow.cancel();
                } else if (position == 18) {
                    setFont("fonts/Pacifico.ttf");
                    alertShow.cancel();
                }
                editor.commit();
                startActivity(new Intent(getContext(), Main.class));
                getActivity().finish();
            }
        });
    }

    private void setFont(String pathFont) {
        tf = Typeface.createFromAsset(getActivity().getAssets(), pathFont);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FONT, pathFont);
        editor.commit();
    }

    public static void createNotification() {
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        long sCount = sharedPreferences.getLong(sCountDay, 0);
        long hieuDay = Calendar.getInstance().getTimeInMillis() - sCount + HIEU_CHINH_THOI_GIAN;
        long countDay = hieuDay / 86400000;

        if (!sharedPreferences.contains(sCountDay))
            remoteViews.setTextViewText(R.id.tvCountDay, "0");
        else
            remoteViews.setTextViewText(R.id.tvCountDay, countDay + "");
        String sBoy = sharedPreferences.getString(sNameMale, "BOY");
        String sGirl = sharedPreferences.getString(sNameFemale, "Girl");
        remoteViews.setTextViewText(R.id.tvBoy, sBoy);
        remoteViews.setTextViewText(R.id.tvGirl, sGirl);

        Notification notification = new NotificationCompat.Builder(context, ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomBigContentView(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    private void ClearNotification(){
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private void setControl(View view) {
        swMK = view.findViewById(R.id.swMK);
        tvFont = view.findViewById(R.id.tvfont);
        swNo = view.findViewById(R.id.swNotification);
        tvcd = view.findViewById(R.id.cd);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }

}