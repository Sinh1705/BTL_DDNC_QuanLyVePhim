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

public class PhimAdapter extends FirebaseRecyclerAdapter<Phim,PhimAdapter.ViewHoleder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PhimAdapter(@NonNull FirebaseRecyclerOptions<Phim> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHoleder holder, int position, @NonNull Phim model) {
        holder.theloai.setText(model.getTheloai());
        holder.ten.setText(model.getTen());
        holder.mota.setText(model.getMota());
        holder.gia.setText(String.valueOf(model.getGia())); //nhớ phần này
        holder.khoichieu.setText(model.getGiokhoichieu());
        Glide.with(holder.anhphim.getContext())
                .load(model.getAnhphim())
                .placeholder(R.drawable.anh_1) // Hiển thị ảnh này trong khi đang tải
                .error(R.drawable.anh_1) // Hiển thị ảnh này nếu có lỗi khi tải
                .into(holder.anhphim);

    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_phim,parent,false);
        return new ViewHoleder(view);
    }

    public class ViewHoleder extends RecyclerView.ViewHolder{

        TextView theloai , ten , mota, gia , khoichieu ;
        ImageView anhphim ;

        public ViewHoleder(@NonNull View itemView) {
            super(itemView);

            theloai = itemView.findViewById(R.id.tv_theloai);
            ten = itemView.findViewById(R.id.tv_ten);
            mota = itemView.findViewById(R.id.tv_mota);
            gia = itemView.findViewById(R.id.tv_gia);
            khoichieu = itemView.findViewById(R.id.tv_khoichieu);
            anhphim = itemView.findViewById(R.id.img_phim);
        }
    }
}
