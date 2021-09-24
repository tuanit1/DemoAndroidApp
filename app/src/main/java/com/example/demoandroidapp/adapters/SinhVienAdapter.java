package com.example.demoandroidapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoandroidapp.R;
import com.example.demoandroidapp.model.SinhVien;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.MyViewHolder> {


    private ArrayList<SinhVien> arrayList_sinhvien;
    private Context context;

    public SinhVienAdapter(ArrayList<SinhVien> arrayList_sinhvien, Context context) {
        this.arrayList_sinhvien = arrayList_sinhvien;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_sinhvien_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        SinhVien sv = arrayList_sinhvien.get(position);

        holder.tv_name.setText(sv.getName());

        SimpleDateFormat formatter =new SimpleDateFormat("dd-MMM-yyyy");

        String birthday = formatter.format(sv.getBirthday());

        holder.tv_bd.setText(birthday);
        holder.tv_address.setText(sv.getAddress());
        holder.tv_age.setText(String.valueOf(sv.getAge()));

        String image_link = "http://192.168.1.10/demoandroid/image/" + sv.getImage();

        Picasso.get()
                .load(image_link)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList_sinhvien.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tv_name, tv_age, tv_bd, tv_address;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_age = itemView.findViewById(R.id.tv_age);
            tv_bd = itemView.findViewById(R.id.tv_bd);

            imageView = itemView.findViewById(R.id.imageview);

        }
    }
}
