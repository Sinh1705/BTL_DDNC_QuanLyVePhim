package com.example.myapplication.user;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class History_Adapter extends FirebaseRecyclerAdapter<Ve,History_Adapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private Context context;
    public History_Adapter(@NonNull FirebaseRecyclerOptions<Ve> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull History_Adapter.ViewHolder holder, int position, @NonNull Ve model) {
        holder.gio.setText(model.getGio());
        holder.maphim.setText(String.valueOf(model.getMaphim()));
        holder.ngaydat.setText(model.getNgaydat());
        holder.phong.setText(model.getPhong());
        holder.soluongve.setText(String.valueOf(model.getSoluongve()));
        holder.tenphim.setText(model.getTenphim());
        holder.tongtien.setText(String.valueOf(model.getTongtien()));
        holder.khoichieu.setText(model.getKhoichieu());
        // Lấy danh sách ghế từ đối tượng Ve
        ArrayList<String> danhSachGhe = model.getGhe();

        // Chuyển danh sách ghế thành chuỗi
        String chuoiDanhSachGhe = convertSeatListToString(danhSachGhe);

        // Hiển thị chuỗi danh sách ghế trong TextView
        holder.danhsachghe.setText(chuoiDanhSachGhe);

//        String khoiChieu = model.getKhoichieu();
//        // So sánh ngày khởi chiếu với ngày hiện tại
//        if (isDateExpired(model.getKhoichieu())) {
//            // Nếu ngày khởi chiếu đã hết hạn, đổi màu nền của item
//            holder.itemView.setBackgroundColor(Color.RED);
//        } else {
//            // Nếu không, đặt màu nền mặc định
//            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Màu nền trong suốt
//        }

    }

    private boolean isDateExpired(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date ticketDate = dateFormat.parse(dateString);
            Date currentDate = new Date(); // Ngày hiện tại
            // So sánh ngày khởi chiếu với ngày hiện tại
            return ticketDate != null && ticketDate.before(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String convertSeatListToString(ArrayList<String> danhSachGhe) {
        return TextUtils.join(", ", danhSachGhe);
    }

    @NonNull
    @Override
    public History_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_history_ticket,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView maphim, soluongve, gio, khoichieu, ngaydat, phong, tenphim, tongtien, danhsachghe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maphim = itemView.findViewById(R.id.tv_history_mave);
            soluongve = itemView.findViewById(R.id.tv_history_soluong);
            gio = itemView.findViewById(R.id.tv_history_gio);
            khoichieu = itemView.findViewById(R.id.tv_history_khoichieu);
            ngaydat = itemView.findViewById(R.id.tv_history_ngaydat);
            phong = itemView.findViewById(R.id.tv_history_phong);
            tenphim = itemView.findViewById(R.id.tv_history_tenve);
            tongtien = itemView.findViewById(R.id.tv_history_tongtien);
            danhsachghe = itemView.findViewById(R.id.tv_history_ghe);
        }
    }
}
