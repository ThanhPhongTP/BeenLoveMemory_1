package com.example.beenlovememory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.beenlovememory.Activity.MyDateSetListener;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import static com.example.beenlovememory.FragmentMain.HIEU_CHINH_THOI_GIAN;

public class UpdateLetterActivity extends AppCompatActivity {

    Button btnSave;
    EditText edtDes;
    TextView tvDate;
    ImageView imgCamera;
    Calendar mCurrentSelectedDate;
    public final int REQUEST_IMAGE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().hide();
        setControl();
        setEvent();
    }

    @SuppressLint("ResourceType")
    private void setEvent() {
        Intent intent = getIntent();
        String sDate = intent.getStringExtra("DATE");
        String sDes = intent.getStringExtra("DES");
        int nImg = intent.getIntExtra("IMG", R.drawable.photo_camera);

        edtDes.setText(sDes);
        tvDate.setText(sDate);
        imgCamera.setImageResource(nImg);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForCalendar(new MyDateSetListener() {
                    @Override
                    public void onDateSet(Calendar currentSelectedDate) {
                        int dayOfMonth = currentSelectedDate.get(Calendar.DAY_OF_MONTH);
                        int month = currentSelectedDate.get(Calendar.MONTH) + 1;
                        int year = currentSelectedDate.get(Calendar.YEAR);
                        tvDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                });
            }
        });
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePickerOptions();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    private void updateData() {
        Intent intent = getIntent();
        String sDate = intent.getStringExtra("DATE");
        String sDes = intent.getStringExtra("DES");

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgCamera.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        byte[] img = byteArray.toByteArray();
        if (!edtDes.getText().toString().equals("") && !tvDate.getText().toString().equals("")) {
            FragmentMemory.database.updateData(
                    tvDate.getText().toString().trim(),
                    edtDes.getText().toString().trim(),
                    img,sDate, sDes

            );
            Toast.makeText(getApplicationContext(), "Đã sửa", Toast.LENGTH_SHORT).show();
            finish();
        } else
            Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
    }


    private void loadProfile(String url) {
        Glide.with(this).load(url)
                .into(imgCamera);
        imgCamera.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
//                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                loadProfile(uri.toString());
            }
        }
    }


    protected void dialogForCalendar(final MyDateSetListener listener) {
        if (mCurrentSelectedDate == null) {
            mCurrentSelectedDate = Calendar.getInstance();
            mCurrentSelectedDate.setTimeInMillis(System.currentTimeMillis());
        }
        final DatePickerDialog mDatePicker = new DatePickerDialog(this,
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
                            Toast.makeText(getApplicationContext(), "Không thể chọn ngày tương lai", Toast.LENGTH_SHORT).show();
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


    private void setControl() {
        btnSave = findViewById(R.id.btnUpdate);
        tvDate = findViewById(R.id.edtDate);
        edtDes = findViewById(R.id.edtDes);
        imgCamera = findViewById(R.id.imgCamera);

    }
}