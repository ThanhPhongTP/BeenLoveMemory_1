package com.example.beenlovememory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beenlovememory.Adapter.RecycleAdapterWallpaper;
import com.example.beenlovememory.Model.Wallpaper;

import java.util.ArrayList;

public class FragmentWallpaper extends Fragment {

    private ArrayList<Wallpaper> wallpapers = new ArrayList<>();
    private RecycleAdapterWallpaper myAdapter;
    private RecyclerView myrv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallpaper, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    private void setEvent() {
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

    private void setControl(View view) {
        myrv = view.findViewById(R.id.rccv);
    }
}