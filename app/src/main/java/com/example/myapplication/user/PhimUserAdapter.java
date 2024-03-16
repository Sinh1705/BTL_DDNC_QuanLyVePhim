package com.example.myapplication.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PhimUserAdapter extends FirebaseRecyclerAdapter<PhimUser,PhimUserAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PhimUserAdapter(@NonNull FirebaseRecyclerOptions<PhimUser> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PhimUserAdapter.myViewHolder holder, int position, @NonNull PhimUser model) {
        holder.ten.setText(model.getTen());
        Glide.with(holder.imageView.getContext())
                .load(model.getAnhPhim())
                .placeholder(R.drawable.anh_1) // Hiển thị ảnh này trong khi đang tải
                .error(R.drawable.anh_1) // Hiển thị ảnh này nếu có lỗi khi tải
                .into(holder.imageView);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phimuser,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView ten;
        ImageView imageView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ten = itemView.findViewById(R.id.txt_theloai);
            imageView = itemView.findViewById(R.id.img_theloai);
        }
    }
}
