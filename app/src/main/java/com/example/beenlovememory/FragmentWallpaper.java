package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.beenlovememory.Adapter.RecycleAdapterWallpaper;
import com.example.beenlovememory.Model.Wallpaper;

import java.util.ArrayList;

import static com.example.beenlovememory.Main.RESULT_FROM_CHANGBG;

public class FragmentWallpaper extends Fragment {

    private ArrayList<Wallpaper> wallpapers = new ArrayList<>();
    private RecycleAdapterWallpaper myAdapter;
    private RecyclerView myrv;
    ImageView imgCamera, imgGallery;
    public final int REQUEST_IMAGE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallpaper, container, false);
        setControl(view);
        addItemRecycle();
        setEvent();
        return view;
    }

    private void setEvent(){
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCameraIntent();
            }
        });
        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGalleryIntent();
            }
        });
    }

    private void addItemRecycle() {
        myAdapter = new RecycleAdapterWallpaper(getActivity(), wallpapers);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        myrv.setAdapter(myAdapter);

        wallpapers.add(new Wallpaper(R.drawable.img_bg1));
        wallpapers.add(new Wallpaper(R.drawable.img_bg10));
        wallpapers.add(new Wallpaper(R.drawable.img_bg11));
        wallpapers.add(new Wallpaper(R.drawable.img_bg12));
        wallpapers.add(new Wallpaper(R.drawable.img_bg13));
        wallpapers.add(new Wallpaper(R.drawable.img_bg14));
        wallpapers.add(new Wallpaper(R.drawable.img_bg15));
        wallpapers.add(new Wallpaper(R.drawable.img_bg16));
        wallpapers.add(new Wallpaper(R.drawable.img_bg17));
        wallpapers.add(new Wallpaper(R.drawable.img_bg18));
        wallpapers.add(new Wallpaper(R.drawable.img_bg19));

        wallpapers.add(new Wallpaper(R.drawable.img_bg2));
        wallpapers.add(new Wallpaper(R.drawable.img_bg20));
        wallpapers.add(new Wallpaper(R.drawable.img_bg21));
        wallpapers.add(new Wallpaper(R.drawable.img_bg22));
        wallpapers.add(new Wallpaper(R.drawable.img_bg23));
        wallpapers.add(new Wallpaper(R.drawable.img_bg24));
        wallpapers.add(new Wallpaper(R.drawable.img_bg26));
        wallpapers.add(new Wallpaper(R.drawable.img_bg25));
        wallpapers.add(new Wallpaper(R.drawable.img_bg27));
        wallpapers.add(new Wallpaper(R.drawable.img_bg28));
        wallpapers.add(new Wallpaper(R.drawable.img_bg29));

        wallpapers.add(new Wallpaper(R.drawable.img_bg3));
        wallpapers.add(new Wallpaper(R.drawable.img_bg31));
        wallpapers.add(new Wallpaper(R.drawable.img_bg32));
        wallpapers.add(new Wallpaper(R.drawable.img_bg30));
        wallpapers.add(new Wallpaper(R.drawable.img_bg33));
        wallpapers.add(new Wallpaper(R.drawable.img_bg34));
        wallpapers.add(new Wallpaper(R.drawable.img_bg36));
        wallpapers.add(new Wallpaper(R.drawable.img_bg35));
        wallpapers.add(new Wallpaper(R.drawable.img_bg37));
        wallpapers.add(new Wallpaper(R.drawable.img_bg38));
        wallpapers.add(new Wallpaper(R.drawable.img_bg39));

        wallpapers.add(new Wallpaper(R.drawable.img_bg4));
        wallpapers.add(new Wallpaper(R.drawable.img_bg41));
        wallpapers.add(new Wallpaper(R.drawable.img_bg42));
        wallpapers.add(new Wallpaper(R.drawable.img_bg43));
        wallpapers.add(new Wallpaper(R.drawable.img_bg44));
        wallpapers.add(new Wallpaper(R.drawable.img_bg45));
        wallpapers.add(new Wallpaper(R.drawable.img_bg46));
        wallpapers.add(new Wallpaper(R.drawable.img_bg47));

        wallpapers.add(new Wallpaper(R.drawable.img_bg5));
        wallpapers.add(new Wallpaper(R.drawable.img_bg6));
        wallpapers.add(new Wallpaper(R.drawable.img_bg7));
        wallpapers.add(new Wallpaper(R.drawable.img_bg8));
        wallpapers.add(new Wallpaper(R.drawable.img_bg9));
        myAdapter.notifyDataSetChanged();
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
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
//                Uri uri = data.getData();
                Intent intent = new Intent(getContext(), Main.class);

                intent.putExtra("ChangeBG", uri.toString());
                startActivity(intent);

//                intent.putExtra("ChangeBG", uri.toString());
//                getActivity().setResult(RESULT_FROM_CHANGBG, intent);

                getActivity().finish();

//                Toast.makeText(getActivity(), uri.toString(), Toast.LENGTH_SHORT).show();
//                loadProfile(uri.toString());
            }
        }
    }

//    private void loadProfile(String url) {
//        Glide.with(this).load(url)
//                .into(imgCamera);
//        imgCamera.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.transparent));
//    }

    private void setControl(View view) {
        myrv = view.findViewById(R.id.rccv);
        imgCamera = view.findViewById(R.id.cam);

        imgGallery = view.findViewById(R.id.gallery);
    }
}