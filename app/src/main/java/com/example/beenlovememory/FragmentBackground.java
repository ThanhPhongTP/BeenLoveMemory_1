package com.example.beenlovememory;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.example.beenlovememory.Activity.MyDateSetListener;
import com.example.beenlovememory.Adapter.FragmentAdapter;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.beenlovememory.FragmentSetting.FONT;
import static com.example.beenlovememory.FragmentSetting.SWITCHNOTI;
import static com.example.beenlovememory.FragmentSetting.createNotification;

public class FragmentBackground extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    LinearLayout lnYOM;
    LinearLayout lnYOF;
    //    int nDay, nMonth, nYear;
    private int colorSelected, colorBgSelected;
    private int currentBackgroundColor = 0xffffffff;
    VectorDrawableCompat.VFullPath dressPath;

    SharedPreferences sharedPreferences;
    public static final String sNameMale = "NAMEMALE";
    public static final String sNameFemale = "NAMEFEMALE";
    String sOldMale = "OLDMALE";
    String sOldFemale = "OLDFEMALE";
    String CHDMALE = "CHDM";
    String CHDFEMALE = "CHDF";
    String sImgBoy = "IMGBOY";
    String sImgGirl = "IMGGIRL";
    String sColorHeart = "COLOR_HEART";
    String sColorOM = "COLOROM";
    String sColorOF = "COLOROF";
    String sColorCHDM = "COLORCHDM";
    String sColorCHDF = "COLORCHDF";
    String sGTM = "MALE";
    String sGTF = "FEMALE";

    //    private final String TAG = FragmentMain.class.getSimpleName();
    public final int REQUEST_IMAGE = 100;
    //    @BindView(R.id.img1)
    ImageView imgProfile;
    public static TextView tvMale, tvFemale, tvTuoiMale, tvTuoiFemale, tvsMale, tvsFemale, tvCHDMale, tvCHDFemale;
    CircleImageView img1, img2;
    Animation a_ImgHeart;
    ImageView ImgHeart;
    public static final int BOY = 1;
    public static final int GIRL = 0;
    public static int CHOOSE_IMAGE;
    public static final int OLD = 1;
    public static final int CHD = 0;
    public static final int COLORHEART = 2;
    public static int OPTION;
    private Typeface tf;

    public static final int HIEU_CHINH_THOI_GIAN = 60000 * 60;// giay 1h

    Calendar mCurrentSelectedDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_background);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_background, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        setControl(view);
        addTabs();
        updateData();
        setEvent();
        setAnimation();
        getFontFromSetting();
        return view;
    }

    private void getFontFromSetting() {
        if (sharedPreferences.contains(FONT)) {
            String sFont = sharedPreferences.getString(FONT, "fonts/Xoxoxa.ttf");
            tf = Typeface.createFromAsset(getActivity().getAssets(), sFont);
            tvMale.setTypeface(tf);
            tvFemale.setTypeface(tf);
            tvTuoiMale.setTypeface(tf);
            tvTuoiFemale.setTypeface(tf);
            tvsMale.setTypeface(tf);
            tvsFemale.setTypeface(tf);
            tvCHDMale.setTypeface(tf);
            tvCHDFemale.setTypeface(tf);
        }
    }

    private void addTabs() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager());
        fragmentAdapter.add(new FragmentMain(), "");
        fragmentAdapter.add(new FragmentDate(), "");
        fragmentAdapter.add(new FragmentLetter(), "");
        viewPager.setAdapter(fragmentAdapter);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackground(null);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void updateData() {
        if (sharedPreferences.contains(sNameMale)) {
            String sNM = sharedPreferences.getString(sNameMale, "Nickname 1");
            tvMale.setText(sNM);
        }
        if (sharedPreferences.contains(sNameFemale)) {
            String sNF = sharedPreferences.getString(sNameFemale, "Nickname 2");
            tvFemale.setText(sNF);
        }
        if (sharedPreferences.contains(sGTM)) {
            String sGT = sharedPreferences.getString(sGTM, "♂");
            tvsMale.setText(sGT);
        }
        if (sharedPreferences.contains(sGTF)) {
            String sGT = sharedPreferences.getString(sGTF, "♀");
            tvsFemale.setText(sGT);
        }
        if (sharedPreferences.contains(sOldMale)) {
            int nOM = sharedPreferences.getInt(sOldMale, 0);
            tvTuoiMale.setText(nOM + "");
        }
        if (sharedPreferences.contains(sOldFemale)) {
            int nOFM = sharedPreferences.getInt(sOldFemale, 0);
            tvTuoiFemale.setText(nOFM + "");
        }
        if (sharedPreferences.contains(CHDMALE)) {
            String s = sharedPreferences.getString(CHDMALE, "Nhân Mã");
            tvCHDMale.setText(s + "");
        }
        if (sharedPreferences.contains(CHDFEMALE)) {
            String s = sharedPreferences.getString(CHDFEMALE, "Nhân Mã");
            tvCHDFemale.setText(s + "");
        }
        if (sharedPreferences.contains(sImgBoy)) {
            String ImgBoy = sharedPreferences.getString(sImgBoy, "");
            Glide.with(this).load(ImgBoy)
                    .into(img1);
            img1.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.transparent));
        }
        if (sharedPreferences.contains(sImgGirl)) {
            String ImgGirl = sharedPreferences.getString(sImgGirl, "");
            Glide.with(this).load(ImgGirl)
                    .into(img2);
            img2.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.transparent));
        }
        if (sharedPreferences.contains(sColorHeart)) {
            int nColor = sharedPreferences.getInt(sColorHeart, currentBackgroundColor);
            changeBackgroundColorImgHeart(nColor);
        }
        else
            changeBackgroundColorImgHeart(R.color.backgroud);
        if (sharedPreferences.contains(sColorOM)) {
            CHOOSE_IMAGE = BOY;
            int nColorOM = sharedPreferences.getInt(sColorOM, currentBackgroundColor);
            changeColorYO(nColorOM);
        }
        if (sharedPreferences.contains(sColorOF)) {
            CHOOSE_IMAGE = GIRL;
            int nColorOF = sharedPreferences.getInt(sColorOF, currentBackgroundColor);
            changeColorYO(nColorOF);
        }
        if (sharedPreferences.contains(sColorCHDM)) {
            CHOOSE_IMAGE = BOY;
            int nColorCHDM = sharedPreferences.getInt(sColorCHDM, currentBackgroundColor);
            changeColorCHD(nColorCHDM);
        }
        if (sharedPreferences.contains(sColorCHDF)) {
            CHOOSE_IMAGE = GIRL;
            int nColorCHDF = sharedPreferences.getInt(sColorCHDF, currentBackgroundColor);
            changeColorCHD(nColorCHDF);
        }
    }

    private void setEvent() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = BOY;
                showDialogOptions1();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = GIRL;
                showDialogOptions1();
            }
        });
        tvMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = BOY;
                showDialogOptions1();
            }
        });
        tvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = GIRL;
                showDialogOptions1();
            }
        });

        tvTuoiMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = BOY;
                OPTION = OLD;
                showDialogOptions2();
            }
        });
        tvTuoiFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = GIRL;
                OPTION = OLD;
                showDialogOptions2();
            }
        });
        tvCHDMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = BOY;
                OPTION = CHD;
                showDialogOptions2();
            }
        });
        tvCHDFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = GIRL;
                OPTION = CHD;
                showDialogOptions2();
            }
        });
        tvsMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = BOY;
                showDialogOptions2();
            }
        });
        tvsFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHOOSE_IMAGE = GIRL;
                showDialogOptions2();
            }
        });
        ImgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OPTION = COLORHEART;
                showColorPickerDialog();
            }
        });
    }

    //put img1
    private void loadProfile(String url) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (CHOOSE_IMAGE == BOY) {
            Glide.with(this).load(url)
                    .into(img1);
            img1.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.transparent));

        } else if (CHOOSE_IMAGE == GIRL) {
            Glide.with(this).load(url)
                    .into(img2);
            img2.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.transparent));
        }
    }

//    private void loadProfileDefault() {
//        Glide.with(getActivity()).load(R.drawable.no_avatar_male)
//                .into(imgProfile);
//        imgProfile.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
//    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getContext(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (CHOOSE_IMAGE == BOY) {
                    Uri uri = data.getParcelableExtra("path");
                    loadProfile(uri.toString());
                    editor.putString(sImgBoy, uri.toString());
                    editor.commit();
                }
                if (CHOOSE_IMAGE == GIRL) {
                    Uri uri = data.getParcelableExtra("path");
                    loadProfile(uri.toString());
                    editor.putString(sImgGirl, uri.toString());
                    editor.commit();
                }

            }
        }
    }

    private void setAnimation() {
        a_ImgHeart = AnimationUtils.loadAnimation(getContext(), R.anim.animation_btnheart);
        ImgHeart.setAnimation(a_ImgHeart);
    }

    private void showColorPickerDialog() {
//        final SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("choose_color")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
//                        colorSelected = adjustAlpha(selectedColor, 0.6f);
                        Toast.makeText(getContext(), "onColorSelected: 0x" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton(("ok"), new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        if (OPTION == COLORHEART) {
                            changeBackgroundColorImgHeart(selectedColor);
                            editor.putInt(sColorHeart, selectedColor);
                            editor.commit();
                        }
                        if (OPTION == OLD) {
                            changeColorYO(selectedColor);
                        }
                        if (OPTION == CHD) {
                            changeColorCHD(selectedColor);
                        }


                    }
                })
                .setNegativeButton(("cancle"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    @SuppressLint("ResourceType")
    private void changeBackgroundColorImgHeart(int selectedColor) {
        currentBackgroundColor = selectedColor;

        VectorChildFinder vector = new VectorChildFinder(getContext(),
                R.drawable.btnheart, ImgHeart);
        dressPath = vector.findPathByName("color_heart");
//        dressPath.setFillColor(getResources().getColor(android.R.color.holo_green_dark));
        dressPath.setFillColor(selectedColor);
        ImgHeart.invalidate();

    }

    private void setBirthday() {
        dialogForCalendar(new MyDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(Calendar currentSelectedDate) {
                //Birthday
                int dayOfMonth = currentSelectedDate.get(Calendar.DAY_OF_MONTH);
                int month = currentSelectedDate.get(Calendar.MONTH) + 1;
                int year = currentSelectedDate.get(Calendar.YEAR);
                //Current Date
                Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH) + 1;
                int y = calendar.get(Calendar.YEAR);

                int yearOlds = Period.between(LocalDate.of(year, month, dayOfMonth), LocalDate.of(y, m, d)).getYears();

                String sCHD = "";
                if ((dayOfMonth >= 21 && month == 3) || (dayOfMonth <= 19 && month == 4))
                    sCHD = "Bạch Dương";
                if ((dayOfMonth >= 20 && month == 4) || (dayOfMonth <= 20 && month == 5))
                    sCHD = "Kim Ngưu";
                if ((dayOfMonth >= 21 && month == 5) || (dayOfMonth <= 20 && month == 6))
                    sCHD = "Song Tử";
                if ((dayOfMonth >= 21 && month == 6) || (dayOfMonth <= 22 && month == 7))
                    sCHD = "Cự Giải";
                if ((dayOfMonth >= 23 && month == 7) || (dayOfMonth <= 22 && month == 8))
                    sCHD = "Sư Tử";
                if ((dayOfMonth >= 23 && month == 8) || (dayOfMonth <= 22 && month == 9))
                    sCHD = "Xử Nữ";
                if ((dayOfMonth >= 23 && month == 9) || (dayOfMonth <= 23 && month == 10))
                    sCHD = "Thiên Bình";
                if ((dayOfMonth >= 23 && month == 10) || (dayOfMonth <= 22 && month == 11))
                    sCHD = "Bọ Cạp";
                if ((dayOfMonth >= 23 && month == 11) || (dayOfMonth <= 21 && month == 12))
                    sCHD = "Nhân Mã";
                if ((dayOfMonth >= 22 && month == 12) || (dayOfMonth <= 19 && month == 1))
                    sCHD = "Ma Kết";
                if ((dayOfMonth >= 20 && month == 1) || (dayOfMonth <= 18 && month == 2))
                    sCHD = "Bảo Bình";
                if ((dayOfMonth >= 19 && month == 2) || (dayOfMonth <= 20 && month == 3))
                    sCHD = "Song Ngư";

                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (CHOOSE_IMAGE == BOY) {
                    tvTuoiMale.setText(yearOlds + "");
                    tvCHDMale.setText(sCHD);
                    editor.putInt(sOldMale, yearOlds);
                    editor.putString(CHDMALE, sCHD);
                    editor.commit();
                } else if (CHOOSE_IMAGE == GIRL) {
                    tvTuoiFemale.setText(yearOlds + "");
                    tvCHDFemale.setText(sCHD);
                    editor.putInt(sOldFemale, yearOlds);
                    editor.putString(CHDFEMALE, sCHD);
                    editor.commit();
                }
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

    private void changeName() {
        if (CHOOSE_IMAGE == BOY) {
            changeNameForMale();
        } else if (CHOOSE_IMAGE == GIRL) {
            changeNameForFemale();
        }
    }

    private void changeNameForFemale() {
        //Layout
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.setname_for_female, null);
        dialogBuilder.setView(dialogView);
        //Set control && show dialog
        final EditText edtFemale = dialogView.findViewById(R.id.edt_ten_female);
        Button btnHuy = dialogView.findViewById(R.id.btn_huy_female);
        Button btnOk = dialogView.findViewById(R.id.btn_ok_female);
        edtFemale.setText(tvFemale.getText().toString());
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
                if (edtFemale.getText().toString().equals("")) {
                    //    Toast.makeText(getContext(), "Nhập tên bạn gái", Toast.LENGTH_SHORT).show();
                    tvFemale.setText("Nickname 2");
                    alertShowMale.cancel();
                } else {
                    tvFemale.setText(edtFemale.getText().toString());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(sNameFemale, edtFemale.getText().toString());
                    editor.commit();
                    alertShowMale.cancel();
                }

                if (sharedPreferences.getBoolean(SWITCHNOTI, false)){
                    Toast.makeText(getContext(), "sda", Toast.LENGTH_SHORT).show();
                    createNotification();
                } else
                    return;
            }
        });
    }

    private void changeNameForMale() {
        //Layout
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.setname_for_male, null);
        dialogBuilder.setView(dialogView);
        //Set control && show dialog
        final EditText edtMale = dialogView.findViewById(R.id.edt_ten_male);
        Button btnHuy = dialogView.findViewById(R.id.btn_huy_male);
        Button btnOk = dialogView.findViewById(R.id.btn_ok_male);
        edtMale.setText(tvMale.getText().toString());
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
                if (edtMale.getText().toString().equals("")) {
                    //    Toast.makeText(getContext(), "Nhập tên bạn trai", Toast.LENGTH_SHORT).show();
                    tvMale.setText("Nickname 1");
                    alertShowMale.cancel();
                } else {
                    tvMale.setText(edtMale.getText().toString());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(sNameMale, edtMale.getText().toString());
                    editor.commit();
                    alertShowMale.cancel();
                }
                if (sharedPreferences.getBoolean(SWITCHNOTI, false)){
                    Toast.makeText(getContext(), "sda", Toast.LENGTH_SHORT).show();
                    createNotification();
                } else
                    return;
            }
        });
    }

    private void changeGender() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (CHOOSE_IMAGE == BOY) {
            if (tvsMale.getText().toString().equals("♀")) {
                tvsMale.setText("♂");
                editor.putString(sGTM, "♂");
                editor.commit();
                return;
            }
            if (tvsMale.getText().toString().equals("♂")) {
                tvsMale.setText("♀");
                editor.putString(sGTM, "♀");
                editor.commit();
                return;
            }
        } else if (CHOOSE_IMAGE == GIRL) {
            if (tvsFemale.getText().toString().equals("♀")) {
                tvsFemale.setText("♂");
                editor.putString(sGTF, "♂");
                editor.commit();
                return;
            }
            if (tvsFemale.getText().toString().equals("♂")) {
                tvsFemale.setText("♀");
                editor.putString(sGTF, "♀");
                editor.commit();
                return;
            }
        }
    }

    //Dialog name, avatar, photo border
    private void showDialogOptions1() {
        final String[] items = {"Đổi ảnh đại diện", "Đổi khung ảnh", "Đổi tên hiển thị"};
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
//        b.setTitle("");
        b.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        showImagePickerOptions();
                        break;
                    case 1:
                        break;
                    case 2:
                        changeName();
                        break;
                }
            }
        });
        b.show();
    }

    //Dialog color, birthday, Zodiac
    private void showDialogOptions2() {
        final String[] items = {"Đổi ngày sinh nhật", "Đổi màu nền", "Đổi giới tính"};
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
//        b.setTitle("");
        b.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        setBirthday();
                        break;
                    case 1:
                        showColorPickerDialog();
                        break;
                    case 2:
                        changeGender();
                        break;
                }
            }
        });
        b.show();
    }

    private void changeColorYO(int selectedColor) {
        currentBackgroundColor = selectedColor;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (CHOOSE_IMAGE == BOY) {
            Drawable background = lnYOM.getBackground();
            if (background instanceof GradientDrawable)
                ((GradientDrawable) background).setColor(selectedColor);
            editor.putInt(sColorOM, selectedColor);
            editor.commit();
        }
        if (CHOOSE_IMAGE == GIRL) {
            Drawable background = lnYOF.getBackground();
            if (background instanceof GradientDrawable)
                ((GradientDrawable) background).setColor(selectedColor);
            editor.putInt(sColorOF, selectedColor);
            editor.commit();
        }
    }

    private void changeColorCHD(int selectedColor) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (CHOOSE_IMAGE == BOY) {
            Drawable background = tvCHDMale.getBackground();
            if (background instanceof GradientDrawable)
                ((GradientDrawable) background).setColor(selectedColor);
            editor.putInt(sColorCHDM, selectedColor);
            editor.commit();
        }
        if (CHOOSE_IMAGE == GIRL) {
            Drawable background = tvCHDFemale.getBackground();
            if (background instanceof GradientDrawable)
                ((GradientDrawable) background).setColor(selectedColor);
            editor.putInt(sColorCHDF, selectedColor);
            editor.commit();
        }
    }

    private void setControl(View view) {
        viewPager = view.findViewById(R.id.viewPager2);
        tabLayout = view.findViewById(R.id.tab_indicator);
        lnYOM = view.findViewById(R.id.lnYOM);
        lnYOF = view.findViewById(R.id.lnYOF);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        ImgHeart = view.findViewById(R.id.imgHeart);
        tvMale = view.findViewById(R.id.tv_male);
        tvFemale = view.findViewById(R.id.tv_female);
        tvCHDFemale = view.findViewById(R.id.btn_cunghoandaofemale);
        tvCHDMale = view.findViewById(R.id.btn_cunghoandaomale);
        tvTuoiMale = view.findViewById(R.id.tv_tuoimale);
        tvTuoiFemale = view.findViewById(R.id.tv_tuoifemale);
        tvsFemale = view.findViewById(R.id.tv_sfemale);
        tvsMale = view.findViewById(R.id.tv_smale);
        sharedPreferences = getActivity().getSharedPreferences("BLM", Context.MODE_PRIVATE);
    }
}