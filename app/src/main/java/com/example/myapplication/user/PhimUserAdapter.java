package com.example.myapplication.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Phim;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PhimUserAdapter extends FirebaseRecyclerAdapter<Phim,PhimUserAdapter.myViewHolder> {
    private Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PhimUserAdapter(@NonNull FirebaseRecyclerOptions<Phim> options,Context context) {
        super(options);
        this.context = context;
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Phim model) {
        holder.ten.setText(model.getTen());
        Glide.with(holder.anhphim.getContext())
                .load(model.getAnhphim())
                .placeholder(R.drawable.anh_1) // Hiển thị ảnh này trong khi đang tải
                .error(R.drawable.anh_1) // Hiển thị ảnh này nếu có lỗi khi tải
                .into(holder.anhphim);
        holder.anhphim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Details_Film.class);
                intent.putExtra("phim_id",getRef(position).getKey());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim_user,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView ten;
        ImageView anhphim;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ten = itemView.findViewById(R.id.txt_phim);
            anhphim = itemView.findViewById(R.id.img_phim);
        }
    }
}
