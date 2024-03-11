package com.example.myapplication.user;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.example.myapplication.TheLoaiAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Category_Adapter extends FirebaseRecyclerAdapter<com.example.myapplication.TheLoai, Category_Adapter.myViewHolder> {



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Category_Adapter(@NonNull FirebaseRecyclerOptions<TheLoai> options) {
        super(options);
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull Category_Adapter.myViewHolder holder, int position, @NonNull TheLoai model) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
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
