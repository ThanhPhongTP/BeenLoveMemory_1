package com.example.beenlovememory.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beenlovememory.ImagePickerActivity;
import com.example.beenlovememory.Main;
import com.example.beenlovememory.Model.Wallpaper;
import com.example.beenlovememory.R;

import java.util.ArrayList;

public class RecycleAdapterWallpaper extends RecyclerView.Adapter<RecycleAdapterWallpaper.MyViewHolder1> {
    private Context context;
    private ArrayList<Wallpaper> data;
    public final int REQUEST_IMAGE = 100;

    public RecycleAdapterWallpaper() {
    }

    public RecycleAdapterWallpaper(Context context, ArrayList<Wallpaper> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public RecycleAdapterWallpaper.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.item_wallpaper, parent, false);
        return new RecycleAdapterWallpaper.MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleAdapterWallpaper.MyViewHolder1 holder, final int position) {

//        holder.tv_Date.setText(data.get(position).getsDate());
//        holder.tv_description.setText(data.get(position).getsDescription());
        Wallpaper wallpaper = data.get(position);

        final int img = wallpaper.getnIMG();

        holder.img_bg.setImageResource(img);
        holder.img_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (position == 0){
//                    Toast.makeText(context, "sa", Toast.LENGTH_SHORT).show();
//                    showImagePickerOptions();
//                }
//                else {
                    Intent intent = new Intent(context, Main.class);
                    intent.putExtra("IMG", img);
                    context.startActivity(intent);
                    ((Activity)context).finish();
//                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {

        TextView tv_Date;
        ImageView img_bg;
        CardView cardView ;
        TextView tv_description;

        public MyViewHolder1(View itemView) {
            super(itemView);
//            tv_Date = (TextView) itemView.findViewById(R.id.tv_date) ;
//            tv_description = (TextView) itemView.findViewById(R.id.tv_desc);
            img_bg = (ImageView) itemView.findViewById(R.id.img_bg);
//            cardView = (CardView) itemView.findViewById(R.id.carditem);
//            btnClose = itemView.findViewById(R.id.btnclose);
        }
    }


    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(context, new ImagePickerActivity.PickerOptionListener() {
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
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        ((Activity)context).startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        ((Activity)context).startActivityForResult(intent, REQUEST_IMAGE);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == REQUEST_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getParcelableExtra("path");
//                loadProfile(uri.toString());
//            }
//        }
//    }
//
//    private void loadProfile(String url) {
//        Glide.with(context).load(url)
//                .into(imgCamera);
//        imgCamera.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
//    }

}
