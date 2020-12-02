package com.example.beenlovememory.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beenlovememory.FragmentMemory;
import com.example.beenlovememory.Model.TimeLine;
import com.example.beenlovememory.R;
import com.example.beenlovememory.ShowImageActivity;
import com.example.beenlovememory.UpdateLetterActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TimeLine> data;


    public RecyclerViewAdapter(Context context, ArrayList<TimeLine> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.item_timeline, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BLM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        holder.tv_Date.setText(data.get(position).getsDate());
        holder.tv_description.setText(data.get(position).getsDescription());
        TimeLine timeLine = data.get(position);
        final byte[] img = timeLine.getImg();
        final Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.img_Memory.setImageBitmap(bitmap);



        holder.cardView.setBackground(null);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.show_memory);

                final TextView tvDes = dialog.findViewById(R.id.tvDes);
                final TextView tvDay = dialog.findViewById(R.id.tvDay);
                ImageView imgMemory = dialog.findViewById(R.id.imgM);
                Button btnClose = dialog.findViewById(R.id.btnclose);
                Button btnDel = dialog.findViewById(R.id.btndel);
                Button btnFix = dialog.findViewById(R.id.btnfix);

                tvDes.setText(holder.tv_description.getText());
                tvDay.setText(holder.tv_Date.getText());
                imgMemory.setImageBitmap(bitmap);
                imgMemory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ShowImageActivity.class);
                        intent.putExtra("IMG",img);
                        context.startActivity(intent);
//                        Toast.makeText(v.getContext(), data.get(position).getImg() + "", Toast.LENGTH_SHORT).show();
                    }
                });

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        FragmentMemory.database = new Database(context, "Memory.sqlite", null, 1);
                        FragmentMemory.database.deleteData(
                                holder.tv_description.getText().toString().trim(),
                                holder.tv_Date.getText().toString().trim()

                        );
                        Toast.makeText(v.getContext(), holder.tv_description.getText(), Toast.LENGTH_SHORT).show();

                        data.remove(position);
                        notifyDataSetChanged();
                        notifyItemRemoved(position);
                        dialog.cancel();

                    }
                });

                btnFix.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UpdateLetterActivity.class);
                        intent.putExtra("DES", holder.tv_description.getText());
                        intent.putExtra("DATE", holder.tv_Date.getText());
                        intent.putExtra("IMG", data.get(position).getImg());
                        context.startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Date;
        ImageView img_Memory;
        CardView cardView ;
        TextView tv_description;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_Date = (TextView) itemView.findViewById(R.id.tv_date) ;
            tv_description = (TextView) itemView.findViewById(R.id.tv_desc);
            img_Memory = (ImageView) itemView.findViewById(R.id.img_memory);
            cardView = (CardView) itemView.findViewById(R.id.carditem);
//            btnClose = itemView.findViewById(R.id.btnclose);
        }
    }

}
