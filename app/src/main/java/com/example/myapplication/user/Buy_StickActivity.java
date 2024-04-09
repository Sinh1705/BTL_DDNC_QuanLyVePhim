package com.example.myapplication.user;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Buy_StickActivity extends AppCompatActivity {
    private TextView tvTen, tvGia;
    private String time = "";
    private String room = "";
    private String time1 = "";
    private String room1 = "";
    private RadioButton radioButton11, radioButton12, radioButton13, radioButton14;
    private RadioButton radioButton21, radioButton22, radioButton23, radioButton24;
    private ArrayList<String> selectedSeats;
    private ArrayList<String> buttonTextList;
    private TextView tv_gio, tv_phong;
    private Button btnDatVe;

    private boolean isTimeAndRoomSelected = false;
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

        buttonTextList= new ArrayList<>();

        String textForBtn1 = "1";
        btn1.setText(textForBtn1);
        buttonTextList.add(textForBtn1);

        String textForBtn2 = "2";
        btn2.setText(textForBtn2);
        buttonTextList.add(textForBtn2);

        String textForBtn3 = "3";
        btn3.setText(textForBtn3);
        buttonTextList.add(textForBtn3);

        String textForBtn4 = "4";
        btn4.setText(textForBtn4);
        buttonTextList.add(textForBtn4);

        String textForBtn5 = "5";
        btn5.setText(textForBtn5);
        buttonTextList.add(textForBtn5);

        String textForBtn6 = "6";
        btn6.setText(textForBtn6);
        buttonTextList.add(textForBtn6);

        String textForBtn7 = "7";
        btn7.setText(textForBtn7);
        buttonTextList.add(textForBtn7);

        String textForBtn8 = "8";
        btn8.setText(textForBtn8);
        buttonTextList.add(textForBtn8);

        String textForBtn9 = "9";
        btn9.setText(textForBtn9);
        buttonTextList.add(textForBtn9);

        String textForBtn10 = "10";
        btn10.setText(textForBtn10);
        buttonTextList.add(textForBtn10);

        String textForBtn11 = "11";
        btn11.setText(textForBtn11);
        buttonTextList.add(textForBtn11);

        String textForBtn12 = "12";
        btn12.setText(textForBtn12);
        buttonTextList.add(textForBtn12);

        String textForBtn13 = "13";
        btn13.setText(textForBtn13);
        buttonTextList.add(textForBtn13);

        String textForBtn14 = "14";
        btn14.setText(textForBtn14);
        buttonTextList.add(textForBtn14);

        String textForBtn15 = "15";
        btn15.setText(textForBtn15);
        buttonTextList.add(textForBtn15);



        btnDatVe = findViewById(R.id.btn_XacNhan);
        tv_gio = findViewById(R.id.textView26);
        tv_phong = findViewById(R.id.textView25);
        //gán giá trị cho tên phim, giá
        String ten = getIntent().getStringExtra("tenphim");
        String gia = getIntent().getStringExtra("gia");
        String strKhoiChieu = getIntent().getStringExtra("khoichieu");
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
                //handleSeats()
                selecttime(time);
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
                selecttime(time);
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
                selecttime(time);
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
                selecttime(time);
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
                slecttime(room);
            }
        });

        radioButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton21.setChecked(false);
                radioButton23.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 2";
                slecttime(room);
            }
        });
        radioButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton21.setChecked(false);
                radioButton22.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 3";
                slecttime(room);
            }
        });

        radioButton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton22.setChecked(false);
                radioButton23.setChecked(false);
                radioButton21.setChecked(false);
                room = "phòng 4";
                slecttime(room);
            }
        });

        selectedSeats = new ArrayList<>();

//        String phong = "phòng 1";
//        String gio = "7AM-8AM";
//        FirebaseDatabase.getInstance().getReference().child("ticket")
//                .orderByChild("tenphim").equalTo(ten)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        ArrayList<String> bookedSeats = new ArrayList<>();
//
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            // Kiểm tra xem có tồn tại "phong" và "gio" trong dataSnapshot không
//                            if (!dataSnapshot.hasChild("phong") || !dataSnapshot.hasChild("gio")) {
//                                continue; // Bỏ qua nếu không tồn tại các trường phong hoặc gio
//                            }
//
//                            // Lấy giá trị của "phong" và "gio" từ dataSnapshot
//                            String phongFromDB = dataSnapshot.child("phong").getValue(String.class);
//                            String gioFromDB = dataSnapshot.child("gio").getValue(String.class);
//
//                            // Kiểm tra và xử lý trường hợp phongFromDB hoặc gioFromDB là null
//                            if (phongFromDB == null || gioFromDB == null) {
//                                continue; // Bỏ qua nếu giá trị là null
//                            }
//
//                            // Kiểm tra điều kiện phòng và khung giờ
//                            if (!phongFromDB.equals(tv_phong.getText()) || !gioFromDB.equals(tv_gio.getText())) {
//                                continue; // Bỏ qua vé không phù hợp và tiếp tục vòng lặp
//                            }
//
//                            // Lấy danh sách các ghế đã đặt
//                            DataSnapshot gheSnapshot = dataSnapshot.child("ghe");
//                            if (gheSnapshot.exists()) {
//                                for (DataSnapshot seatSnapshot : gheSnapshot.getChildren()) {
//                                    String seat = seatSnapshot.getValue(String.class); // Lấy key của ghế
//                                    bookedSeats.add(seat); // Thêm ghế vào danh sách đã đặt
//                                }
//                            }
//                        }
//
//                        // Cập nhật giao diện người dùng để hiển thị các ghế đã đặt
//                        String seatsString = convertSeatListToString(bookedSeats);
//                        //hienthi.setText(seatsString);
//
//                        updateSeatButtons(bookedSeats,buttonTextList);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        // Xử lý lỗi nếu cần
//                        Log.e("Firebase", "Lỗi khi đọc dữ liệu từ Firebase", error.toException());
//                    }
//                });

        //queryBookedSeatsForTimeAndRoom(time,room);
        checkTimeAndRoom();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("1")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("1");
                    btn1.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("1");
                    btn1.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("2")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("2");
                    btn2.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("2");
                    btn2.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("3")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("3");
                    btn3.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("3");
                    btn3.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("4")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("4");
                    btn4.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("4");
                    btn4.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("5")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("5");
                    btn5.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("5");
                    btn5.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("6")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("6");
                    btn6.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("6");
                    btn6.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("7")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("7");
                    btn7.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("7");
                    btn7.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));

                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("8")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("8");
                    btn8.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("8");
                    btn8.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("9")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("9");
                    btn9.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("9");
                    btn9.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("10")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("10");
                    btn10.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("10");
                    btn10.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("11")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("11");
                    btn11.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("11");
                    btn11.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("12")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("12");
                    btn12.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("12");
                    btn12.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("13")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("13");
                    btn13.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("13");
                    btn13.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("14")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("btn_14");
                    btn14.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("14");
                    btn14.setBackgroundColor(getResources().getColor(R.color.selected_seat_color));
                }
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedSeats.contains("15")){
                    // Nếu đã chọn, hãy bỏ chọn ghế và cập nhật giao diện người dùng
                    selectedSeats.remove("15");
                    btn15.setBackgroundColor(getResources().getColor(R.color.default_seat_color));
                }else {
                    selectedSeats.add("15");
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
                TextView khoichieu = view1.findViewById(R.id.dailog_khoichieu);
                TextView soluong = view1.findViewById(R.id.dailog_soluongve);
                tenphim.setText(tvTen.getText().toString());
                giochieu.setText(time);
                phong.setText(room);
                khoichieu.setText(strKhoiChieu);

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
                soluong.setText(String.valueOf(finalSoluongghe));
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
                        map.put("khoichieu",khoichieu.getText().toString());

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

    private void checkTimeAndRoom() {
        if (!time.isEmpty() && !room.isEmpty()) {
            // Đã chọn cả giờ và phòng, thực hiện truy vấn Firebase để kiểm tra ghế đã đặt
            isTimeAndRoomSelected = true;

            FirebaseDatabase.getInstance().getReference().child("ticket")
                    .orderByChild("tenphim").equalTo(tvTen.getText().toString())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ArrayList<String> bookedSeats = new ArrayList<>();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String gioFromDB = dataSnapshot.child("gio").getValue(String.class);
                                String phongFromDB = dataSnapshot.child("phong").getValue(String.class);

                                // Kiểm tra nếu giờ và phòng trong Firebase khớp với giờ và phòng đã chọn
                                if (gioFromDB != null && phongFromDB != null &&
                                        gioFromDB.equals(time1) && phongFromDB.equals(room1)) {

                                    // Lấy danh sách các ghế đã đặt
                                    DataSnapshot gheSnapshot = dataSnapshot.child("ghe");
                                    if (gheSnapshot.exists()) {
                                        for (DataSnapshot seatSnapshot : gheSnapshot.getChildren()) {
                                            String seat = seatSnapshot.getValue(String.class);
                                            bookedSeats.add(seat);
                                        }
                                    }
                                }
                            }

                            // Cập nhật giao diện người dùng để hiển thị các ghế đã đặt
                            updateSeatButtons(bookedSeats, buttonTextList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý lỗi nếu có
                            Log.e("Firebase", "Lỗi khi đọc dữ liệu từ Firebase", error.toException());
                        }
                    });
        }
    }

    private void slecttime(String room) {
        room1 = room;
        checkTimeAndRoom();
    }

    private void selecttime(String time) {
        time1 = time;
        checkTimeAndRoom();
    }

    private String convertSeatListToString(ArrayList<String> bookedSeats) {
        return TextUtils.join(", ", bookedSeats);
    }




    private void updateSeatButtons(ArrayList<String> bookedSeats, ArrayList<String> buttonTextList) {
        if (buttonTextList == null || buttonTextList.isEmpty()) {
            // Nếu không có ghế được chọn, không cần thực hiện bất kỳ hành động nào
            return;
        }
        for (String seatId : buttonTextList) {
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
            }else {
                Toast.makeText(Buy_StickActivity.this, "Danh sach ghe rong", Toast.LENGTH_SHORT).show();

            }
        }
    }


    @Nullable
    private Button getSeatButtonById(@NonNull String seatId) {
        switch (seatId){
            case "1" :
                return findViewById(R.id.btn_1);
            case "2" :
                return findViewById(R.id.btn_2);
            case "3" :
                return findViewById(R.id.btn_3);
            case "4" :
                return findViewById(R.id.btn_4);
            case "5" :
                return findViewById(R.id.btn_5);
            case "6" :
                return findViewById(R.id.btn_6);
            case "7" :
                return findViewById(R.id.btn_7);
            case "8" :
                return findViewById(R.id.btn_8);
            case "9" :
                return findViewById(R.id.btn_9);
            case "10" :
                return findViewById(R.id.btn_10);
            case "11" :
                return findViewById(R.id.btn_11);
            case "12" :
                return findViewById(R.id.btn_12);
            case "13" :
                return findViewById(R.id.btn_13);
            case "14" :
                return findViewById(R.id.btn_14);
            case "15" :
                return findViewById(R.id.btn_15);
            default:
                return null;
        }
    }

}
