package com.example.myapplication.user;

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
import com.orhanobut.dialogplus.OnItemClickListener;


public class searchview_adapter extends FirebaseRecyclerAdapter<Phim, searchview_adapter.ViewHolder> {

    private OnItemClickListener itemClickListener;

    private Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public searchview_adapter(@NonNull FirebaseRecyclerOptions<Phim> options) {
        super(options);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Phim model) {
        holder.ten.setText(model.getTen());
        Glide.with(holder.anhphim.getContext())
                .load(model.getAnhphim())
                .placeholder(R.drawable.anh_1) // Hiển thị ảnh này trong khi đang tải
                .error(R.drawable.anh_1) // Hiển thị ảnh này nếu có lỗi khi tải
                .into(holder.anhphim);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.onItemClick(model, position);
                }
            }
        });
    }

    @NonNull
    @Override
    public searchview_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim_user,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView ten;
        ImageView anhphim;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.txt_phim);
            anhphim = itemView.findViewById(R.id.img_phim);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(Phim phim, int position);
    }
}
