package com.example.beenlovememory.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beenlovememory.Main;
import com.example.beenlovememory.Model.Wallpaper;
import com.example.beenlovememory.R;

import java.util.ArrayList;

public class RecycleAdapterWallpaper extends RecyclerView.Adapter<RecycleAdapterWallpaper.MyViewHolder1> {
    private Context context;
    private ArrayList<Wallpaper> data;

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
    public void onBindViewHolder(@NonNull final RecycleAdapterWallpaper.MyViewHolder1 holder, int position) {

//        holder.tv_Date.setText(data.get(position).getsDate());
//        holder.tv_description.setText(data.get(position).getsDescription());
        Wallpaper wallpaper = data.get(position);

        final int img = wallpaper.getnIMG();

        holder.img_bg.setImageResource(img);
        holder.img_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main.class);
                intent.putExtra("IMG", img);
                context.startActivity(intent);
                ((Activity)context).finish();
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
}
