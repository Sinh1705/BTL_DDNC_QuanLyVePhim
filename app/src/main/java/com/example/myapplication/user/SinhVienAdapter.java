package com.example.myapplication.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.Firebase;

public class SinhVienAdapter extends FirebaseRecyclerAdapter<SinhVien,SinhVienAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public SinhVienAdapter(@NonNull FirebaseRecyclerOptions<SinhVien> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SinhVienAdapter.ViewHolder holder, int position, @NonNull SinhVien model) {
        holder.masv.setText(model.getMasinhvien());
        holder.hoten.setText(model.getHoten());
        holder.diachi.setText(model.getDiachi());
        holder.ngaysinh.setText(model.getNgaysinh());
        holder.emailsv.setText(model.getEmail());
        holder.gioitinh.setText(model.getGioitinh());
        holder.diemsv.setText(String.valueOf(model.getDiem()));

    }

    @NonNull
    @Override
    public SinhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinhvien,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView masv, hoten, diachi , ngaysinh, emailsv, gioitinh, diemsv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            masv = itemView.findViewById(R.id.tv_masinhvien);
            hoten = itemView.findViewById(R.id.tv_hotensinhvien);
            diachi = itemView.findViewById(R.id.tv_diachi);
            ngaysinh = itemView.findViewById(R.id.tv_ngaysinh);
            emailsv = itemView.findViewById(R.id.tv_email);
            gioitinh = itemView.findViewById(R.id.tv_gioitinh);
            diemsv = itemView.findViewById(R.id.tv_diemhoctap);
        }
    }
}
