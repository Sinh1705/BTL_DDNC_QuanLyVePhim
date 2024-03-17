package com.example.myapplication.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

public class Banner_Adapter extends RecyclerView.Adapter<Banner_Adapter.BannerViewHolder>{
    private List<String> imgbanner;
    public Banner_Adapter(List<String> imgbanner){
        this.imgbanner = imgbanner;
    }
    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_sligeimage, parent,false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        String imgUrl = imgbanner.get(position);
        Glide.with(holder.itemView.getContext()).load(imgUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imgbanner.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public BannerViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.img_slide);
        }
    }
}
