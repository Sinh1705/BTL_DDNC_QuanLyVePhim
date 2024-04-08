package com.example.myapplication.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.AddTheLoai;
import com.example.myapplication.HomeFragment;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Buy_StickActivity extends AppCompatActivity {
    private TextView tvTen, tvGia;
    private String time = "";
    private String room = "";
    private RadioButton radioButton11, radioButton12, radioButton13, radioButton14;
    private RadioButton radioButton21, radioButton22, radioButton23, radioButton24;
    private ArrayList<String> selectedSeats;
    private Button btnDatVe;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTen = findViewById(R.id.ticket_tenphim);
        tvGia = findViewById(R.id.ticket_giaphim);
        radioButton11 = findViewById(R.id.radioButton11);
        radioButton12 = findViewById(R.id.radioButton12);
        radioButton13 = findViewById(R.id.radioButton13);
        radioButton14 = findViewById(R.id.radioButton14);
        radioButton21 = findViewById(R.id.radioButton21);
        radioButton22 = findViewById(R.id.radioButton22);
        radioButton23 = findViewById(R.id.radioButton23);
        radioButton24 = findViewById(R.id.radioButton24);

        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);
        Button btn10 = findViewById(R.id.btn_10);
        Button btn11 = findViewById(R.id.btn_11);
        Button btn12 = findViewById(R.id.btn_12);
        Button btn13 = findViewById(R.id.btn_13);
        Button btn14 = findViewById(R.id.btn_14);
        Button btn15 = findViewById(R.id.btn_15);

        btnDatVe = findViewById(R.id.btn_XacNhan);

        //gán giá trị cho tên phim, giá
        String ten = getIntent().getStringExtra("tenphim");
        String gia = getIntent().getStringExtra("gia");
        tvTen.setText(ten);
        tvGia.setText(String.valueOf(gia));


        //xử lý sự kiện khi click vào chọn giờ chiếu phim
        radioButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi chọn thời gian 7AM-8AM
                Toast.makeText(Buy_StickActivity.this, "7AM-8AM được chọn", Toast.LENGTH_SHORT).show();
                radioButton12.setChecked(false);
                radioButton13.setChecked(false);
                radioButton14.setChecked(false);
                time ="7AM-8AM";
                //handleSeats();
            }

//            private void handleSeats() {
//                if(time.equals("7AM-8AM")){
//                    btn1.setBackgroundTintList(getResources().getColorStateList(R.color.selected_seat_color));
//                    btn1.setEnabled(false);
//                    Toast.makeText(Buy_StickActivity.this, "Đã thay đổi giá trị button", Toast.LENGTH_SHORT).show();
//                }
//            }
        });

        radioButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi chọn thời gian 8AM-9AM
                Toast.makeText(Buy_StickActivity.this, "8AM-9AM được chọn", Toast.LENGTH_SHORT).show();
                radioButton11.setChecked(false);
                radioButton13.setChecked(false);
                radioButton14.setChecked(false);
                time ="8AM-9AM";
            }
        });

        radioButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi chọn thời gian 9AM-10AM
                Toast.makeText(Buy_StickActivity.this, "9AM-10AM được chọn", Toast.LENGTH_SHORT).show();
                radioButton11.setChecked(false);
                radioButton12.setChecked(false);
                radioButton14.setChecked(false);
                time ="9AM-10AM";
            }
        });

        radioButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi chọn thời gian 10AM-11AM
                Toast.makeText(Buy_StickActivity.this, "10AM-11AM được chọn", Toast.LENGTH_SHORT).show();
                radioButton11.setChecked(false);
                radioButton12.setChecked(false);
                radioButton13.setChecked(false);
                time ="10AM-11AM";
            }
        });


        //xử lý khi chọn phòng chiếu phim
        radioButton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton22.setChecked(false);
                radioButton23.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 1";
            }
        });

        radioButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton21.setChecked(false);
                radioButton23.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 2";
            }
        });
        radioButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton21.setChecked(false);
                radioButton22.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 3";
            }
        });

        radioButton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton22.setChecked(false);
                radioButton23.setChecked(false);
                radioButton21.setChecked(false);
                room = "phòng 4";
            }
        });
        selectedSeats = new ArrayList<>();


        queryBookedSeatsForTimeAndRoom(time,room);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_1")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_1");
                    btn1.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_1");
                    btn1.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_2")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_2");
                    btn2.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_2");
                    btn2.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_3")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_3");
                    btn3.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_3");
                    btn3.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_4")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_4");
                    btn4.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_4");
                    btn4.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_5")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_5");
                    btn5.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_5");
                    btn5.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_6")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_6");
                    btn6.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_6");
                    btn6.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_7")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_7");
                    btn7.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_7");
                    btn7.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));

                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_8")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_8");
                    btn8.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_8");
                    btn8.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_9")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_9");
                    btn9.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_9");
                    btn9.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_10")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_10");
                    btn10.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_10");
                    btn10.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_11")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_11");
                    btn11.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_11");
                    btn11.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_12")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_12");
                    btn12.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_12");
                    btn12.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_13")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_13");
                    btn13.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_13");
                    btn13.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_14")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_14");
                    btn14.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_14");
                    btn14.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("btn_15")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_15");
                    btn15.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("btn_15");
                    btn15.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = LayoutInflater.from(Buy_StickActivity.this).inflate(R.layout.user_dialog_xacnhan,null);

                //xử lý sự kiện và gán dữ liệu lên textview
                TextView tenphim = view1.findViewById(R.id.dailog_tenphim);
                TextView giochieu = view1.findViewById(R.id.dailog_giochieu);
                TextView phong = view1.findViewById(R.id.dailog_phongchieu);
                TextView ghe = view1.findViewById(R.id.dailog_ghechon);
                TextView total = view1.findViewById(R.id.dailog_total);
                tenphim.setText(tvTen.getText().toString());
                giochieu.setText(time);
                phong.setText(room);

                //xử lý chọn danh sách ghế
                StringBuilder sb = new StringBuilder();
                for (String seat : selectedSeats) {
                    sb.append(seat).append(", ");
                }
                String selectedSeatsString = sb.toString();
                // Xóa dấu phẩy ở cuối
                if (selectedSeatsString.length() > 0) {
                    selectedSeatsString = selectedSeatsString.substring(0, selectedSeatsString.length() - 2);
                }
                ghe.setText(selectedSeatsString);

                //xử lý tổng tiền
                int soluongghe = 0;
                float giave = Float.parseFloat(tvGia.getText().toString());
                for (int i = 0 ; i < selectedSeats.size() ; i++){
                    soluongghe++;
                }
                float  tongtien = giave * soluongghe ;
                total.setText(String.valueOf(tongtien));

                //thêm trường mã vé phim , ngày đặt , tên tài khoản đặt , số lượng vé
                //mã vé randoom
                Random random = new Random();
                int randomNumber = random.nextInt(90000)+10000;

                //lấy ra ngày đặt hiện tại
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String dateString = dateFormat.format(currentDate);

                //lấy ra tên tài khoản
                String tenTK = "";
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser != null){
                    tenTK = firebaseUser.getEmail();
                }

                // Tạo dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Buy_StickActivity.this);
                builder.setView(view1);


                String finalTenTK = tenTK;
                int finalSoluongghe = soluongghe;
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("tenphim",tvTen.getText().toString());
                        map.put("phong",phong.getText().toString());
                        map.put("gio",giochieu.getText().toString());
                        map.put("ghe",selectedSeats);
                        map.put("tongtien",tongtien);
                        map.put("maphim",randomNumber);
                        map.put("ngaydat",dateString.toString());
                        map.put("taikhoandat", finalTenTK.toString());
                        map.put("soluongve", finalSoluongghe);

                        FirebaseDatabase.getInstance().getReference().child("ticket").push()
                                .setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Buy_StickActivity.this,"Data insert successfully",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(Buy_StickActivity.this,"Data not insert successfully",Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

                // Thiết lập nút "Cancel" cho dialog
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // Hiển thị dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    private void queryBookedSeatsForTimeAndRoom(String time, String room) {
        FirebaseDatabase.getInstance().getReference().child("ticket")
                .orderByChild("gio").equalTo(time)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Danh sách ghế đã đặt trong thời gian và phòng đã chọn
                        List<String> bookedSeats = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String phongFromDB = dataSnapshot.child("phong").getValue(String.class);
                            // Kiểm tra nếu phòng từ cơ sở dữ liệu không trùng khớp với phòng đã chọn
                            if (!phongFromDB.equals(room)) {
                                continue; // Bỏ qua vé này và tiếp tục vòng lặp
                            }

                            // Lấy danh sách ghế từ cơ sở dữ liệu
                            DataSnapshot gheSnapshot = dataSnapshot.child("ghe");
                            if (gheSnapshot.exists()) {
                                // Kiểm tra xem có nhiều ghế không
                                if (gheSnapshot.getChildrenCount() > 1) {
                                    // Nếu có nhiều ghế, lặp qua từng ghế
                                    for (DataSnapshot seatSnapshot : gheSnapshot.getChildren()) {
                                        String seat = seatSnapshot.getValue(String.class);
                                        // Kiểm tra xem ghế đã tồn tại trong bookedSeats chưa
                                        if (!bookedSeats.contains(seat)) {
                                            bookedSeats.add(seat);
                                        }
                                    }
                                } else {
                                    // Nếu chỉ có một ghế, lấy giá trị ghế trực tiếp
                                    String seat = gheSnapshot.getValue(String.class);
                                    // Kiểm tra xem ghế đã tồn tại trong bookedSeats chưa
                                    if (!bookedSeats.contains(seat)) {
                                        bookedSeats.add(seat);
                                    }
                                }
                            }
                        }

                        // Cập nhật giao diện người dùng để ngăn chọn những ghế đã đặt
                        updateSeatButtons(bookedSeats);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý lỗi nếu cần
                    }
                });
    }

    private void updateSeatButtons(List<String> bookedSeats) {
        if (selectedSeats == null || selectedSeats.isEmpty()) {
            // Nếu không có ghế được chọn, không cần thực hiện bất kỳ hành động nào
            return;
        }

        for (String seatId : selectedSeats) {
            if (seatId != null) {
                // Kiểm tra xem ghế đã được đặt hay chưa
                boolean isSeatBooked = false;
                for (String bookedSeat : bookedSeats) {
                    if (bookedSeat.contains(seatId)) {
                        isSeatBooked = true;
                        break;
                    }
                }

                Button seatButton = getSeatButtonById(seatId);
                if (seatButton != null) {
                    if (isSeatBooked) {
                        // Nếu ghế đã được đặt, vô hiệu hóa nút và đặt trạng thái
                        seatButton.setEnabled(false);
                        seatButton.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                    } else {
                        // Nếu ghế chưa được đặt, kích hoạt nút
                        seatButton.setEnabled(true);
                        seatButton.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                    }
                }
            }
        }
    }


    @Nullable
    private Button getSeatButtonById(@NonNull String seatId) {
        switch (seatId){
            case "btn_1" :
                return findViewById(R.id.btn_1);
            case "btn_2" :
                return findViewById(R.id.btn_2);
            case "btn_3" :
                return findViewById(R.id.btn_3);
            case "btn_4" :
                return findViewById(R.id.btn_4);
            case "btn_5" :
                return findViewById(R.id.btn_5);
            case "btn_6" :
                return findViewById(R.id.btn_6);
            case "btn_7" :
                return findViewById(R.id.btn_7);
            case "btn_8" :
                return findViewById(R.id.btn_8);
            case "btn_9" :
                return findViewById(R.id.btn_9);
            case "btn_10" :
                return findViewById(R.id.btn_10);
            case "btn_11" :
                return findViewById(R.id.btn_11);
            case "btn_12" :
                return findViewById(R.id.btn_12);
            case "btn_13" :
                return findViewById(R.id.btn_13);
            case "btn_14" :
                return findViewById(R.id.btn_14);
            case "btn_15" :
                return findViewById(R.id.btn_15);
            default:
                return null;
        }
    }

}
