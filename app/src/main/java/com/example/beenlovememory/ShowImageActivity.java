package com.example.beenlovememory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShowImageActivity extends AppCompatActivity {

    ImageView img, imgClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        getSupportActionBar().hide();
        setControl();
        setEvent();
    }

    private void setEvent(){
        Intent intent = getIntent();
        byte[] bimg = intent.getByteArrayExtra("IMG");
        final Bitmap bitmap = BitmapFactory.decodeByteArray(bimg, 0, bimg.length);
        img.setImageBitmap(bitmap);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setControl() {
        img = findViewById(R.id.image);
        imgClose = findViewById(R.id.close);
    }
}