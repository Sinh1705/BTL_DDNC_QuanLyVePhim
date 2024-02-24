package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.ViewHolder> {
    Context context;
    private List<Phim> listPhim;

    public PhimAdapter(List<Phim> listPhim){
        this.listPhim = listPhim;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gán view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_phim,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phim phim = listPhim.get(position);
        holder.imageView.setImageResource(phim.getHinh());
        holder.txtTen.setText(phim.getTen());
        holder.txtTheloai.setText(phim.getTheloai());
        holder.txtMota.setText(phim.getMota());
        holder.txtGia.setText((int) phim.getGia());
        holder.txtKhoichieu.setText(phim.getGiokhoichieu());

    }


    @Override
    public int getItemCount() {
        return listPhim.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTen , txtTheloai , txtMota , txtKhoichieu , txtGia;
        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.img_phim);
            txtTen = view.findViewById(R.id.tv_ten);
            txtTheloai = view.findViewById(R.id.tv_ten);
            txtMota = view.findViewById(R.id.tv_ten);
            txtKhoichieu = view.findViewById(R.id.tv_ten);
            txtGia = view.findViewById(R.id.tv_khoichieu);
        }
    }
}
*/

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {

    private List<Phim> mlistPhim;

    public PhimAdapter(List<Phim> mlistPhim){
        this.mlistPhim = mlistPhim;
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_phim,parent,false);
        return new PhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, int position) {
        Phim phim = mlistPhim.get(position);
        if(phim == null){
            return;
        }
        holder.imageView.setImageResource(phim.getHinh());
        holder.txtTen.setText(phim.getTen());
        holder.txtTheloai.setText(phim.getTheloai());
        holder.txtMota.setText(phim.getMota());
        holder.txtGia.setText(String.valueOf(phim.getGia()));
        holder.txtKhoichieu.setText(phim.getGiokhoichieu());
    }

    @Override
    public int getItemCount() {
        if(mlistPhim!=null){
            return mlistPhim.size();
        }
        return 0;
    }

    class PhimViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtTen , txtTheloai , txtMota , txtKhoichieu , txtGia;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_phim);
            txtTen = itemView.findViewById(R.id.tv_ten);
            txtTheloai = itemView.findViewById(R.id.tv_theloai);
            txtMota = itemView.findViewById(R.id.tv_mota);
            txtKhoichieu = itemView.findViewById(R.id.tv_khoichieu);
            txtGia = itemView.findViewById(R.id.tv_gia);
        }
    }
}