package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class TheLoaiAdapter extends FirebaseRecyclerAdapter<TheLoai,TheLoaiAdapter.myViewHolder> {

    //khai báo 1 interface để lắng nghe sự kiện click
    public interface OnDeleteItemClickListenner{
        void OnDeleteItemClick(int position);
    }

    //khai báo interface để lắng nghe sự kiện click_update
    public interface OnUpdateItemClickListenner{
        void OnUpdateItemClick(int position);
    }

    //thiết lập trình lắng nghe cho adapter
    private OnDeleteItemClickListenner onDeleteItemClickListenner;
    private OnUpdateItemClickListenner onUpdateItemClickListenner;
    public void setOnDeleteItemClickListenner(OnDeleteItemClickListenner listenner){
        this.onDeleteItemClickListenner = listenner;
    }
    public void setOnUpdateItemClickListenner(OnUpdateItemClickListenner listenner){
        this.onUpdateItemClickListenner = listenner;
    }


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TheLoaiAdapter(@NonNull FirebaseRecyclerOptions<TheLoai> options) {
        super(options);
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TheLoai model) {
        holder.theloai.setText(model.getTheloai());
        Glide.with(holder.imageView.getContext())
                .load(model.getLinkanh())
                .placeholder(R.drawable.anh_1) // Hiển thị ảnh này trong khi đang tải
                .error(R.drawable.anh_1) // Hiển thị ảnh này nếu có lỗi khi tải
                .into(holder.imageView);

        //xóa
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = holder.getAdapterPosition();
                if (onDeleteItemClickListenner != null && current_position != RecyclerView.NO_POSITION) {
                    onDeleteItemClickListenner.OnDeleteItemClick(current_position);
                }
            }
        });

        //sửa
        holder.img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img_update.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_theloai))
                        .setExpanded(true,1200)
                        .create();

                View view1 = dialogPlus.getHolderView();

                EditText name = view1.findViewById(R.id.edt_update_ten);
                EditText linkanh = view1.findViewById(R.id.edt_update_linkanh);
                Button btn_update = view1.findViewById(R.id.btn_update_theloai);

                name.setText(model.getTheloai());
                linkanh.setText(model.getLinkanh());

                dialogPlus.show();

                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("theloai",name.getText().toString());
                        map.put("linkanh",linkanh.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("category")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //Toast.makeText(holder.name,"Data insert successfully",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Toast.makeText(AddTheLoai.this,"Data insert successfully",Toast.LENGTH_SHORT).show();
                                        Log.e("UpdateData", "Update failed", e);
                                    }
                                });
                    }
                });

            }
        });


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
        ImageView img_delete , img_update;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            theloai = itemView.findViewById(R.id.txt_theloai);
            imageView = itemView.findViewById(R.id.img_theloai);
            img_delete = itemView.findViewById(R.id.img_delete_theloai);
            img_update = itemView.findViewById(R.id.img_sua_theloai);
        }
    }
}
