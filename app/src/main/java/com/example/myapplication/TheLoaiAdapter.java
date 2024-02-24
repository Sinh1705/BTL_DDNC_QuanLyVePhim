package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TheLoaiAdapter extends FirebaseRecyclerAdapter<TheLoai,TheLoaiAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TheLoaiAdapter(@NonNull FirebaseRecyclerOptions<TheLoai> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TheLoai model) {
        holder.theloai.setText(model.getTheloai());
        Glide.with(holder.imageView.getContext())
                .load(model.getLinkanh())
                .placeholder(R.drawable.anh_1) // Hiển thị ảnh này trong khi đang tải
                .error(R.drawable.anh_1) // Hiển thị ảnh này nếu có lỗi khi tải
                .into(holder.imageView);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_theloai,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView theloai;
        ImageView imageView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            theloai = itemView.findViewById(R.id.txt_theloai);
            imageView = itemView.findViewById(R.id.img_theloai);
        }
    }
}
