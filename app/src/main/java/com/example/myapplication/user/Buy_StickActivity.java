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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Buy_StickActivity extends AppCompatActivity {
    private TextView tvTen, tvGia;
    private String time = "";
    private String room = "";
    private RadioButton radioButton11, radioButton12, radioButton13, radioButton14;
    private RadioButton radioButton21, radioButton22, radioButton23, radioButton24;
    private ArrayList<String> selectedSeats;
    private Button btnDatVe;
    private List<Button> seatButtonsList;
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

        seatButtonsList = new ArrayList<>();

        seatButtonsList.add(btn1);
        seatButtonsList.add(btn2);
        seatButtonsList.add(btn3);
        seatButtonsList.add(btn4);
        seatButtonsList.add(btn5);
        seatButtonsList.add(btn6);
        seatButtonsList.add(btn7);
        seatButtonsList.add(btn8);
        seatButtonsList.add(btn9);
        seatButtonsList.add(btn10);
        seatButtonsList.add(btn11);
        seatButtonsList.add(btn12);
        seatButtonsList.add(btn13);
        seatButtonsList.add(btn14);
        seatButtonsList.add(btn15);



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
                checkConditionsAndQuery();
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
                checkConditionsAndQuery();
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
                checkConditionsAndQuery();
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
                checkConditionsAndQuery();
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
                checkConditionsAndQuery();
            }
        });

        radioButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton21.setChecked(false);
                radioButton23.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 2";
                checkConditionsAndQuery();
            }
        });
        radioButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton21.setChecked(false);
                radioButton22.setChecked(false);
                radioButton24.setChecked(false);
                room = "phòng 3";
                checkConditionsAndQuery();
            }
        });

        radioButton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton22.setChecked(false);
                radioButton23.setChecked(false);
                radioButton21.setChecked(false);
                room = "phòng 4";
                checkConditionsAndQuery();
            }
        });


        selectedSeats = new ArrayList<>();
        String phong = room.toString();
        String thoigian = time.toString();
        checkConditionsAndQuery();



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
                TextView count = view1.findViewById(R.id.dailog_soluongve);
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
                count.setText(String.valueOf(soluongghe));


                // Tạo dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Buy_StickActivity.this);
                builder.setView(view1);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("tenphim",tvTen.getText().toString());
                        map.put("phong",phong.getText().toString());
                        map.put("gio",giochieu.getText().toString());
                        map.put("ghe",selectedSeats);
                        map.put("tongtien",tongtien);
                        FirebaseDatabase.getInstance().getReference().child("ve").push()
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



    private void checkConditionsAndQuery() {
        if (!time.isEmpty() && !room.isEmpty()) {
            // Nếu người dùng đã chọn cả thời gian và phòng chiếu phim, thực hiện truy vấn ghế đã đặt
            queryBookedSeatsForTimeAndRoom(tvTen.getText().toString(), room, time);
        } else {
            // Nếu chưa đủ thông tin, hiển thị thông báo yêu cầu người dùng hoàn tất thông tin
            Toast.makeText(Buy_StickActivity.this, "Vui lòng chọn thời gian và phòng chiếu phim", Toast.LENGTH_SHORT).show();
        }
    }

    private void queryBookedSeatsForTimeAndRoom(String movieName, String room, String time) {
        // Truy vấn dữ liệu từ Firebase
        Map<String, Boolean> seatStatusMap = new HashMap<>();

        FirebaseDatabase.getInstance().getReference().child("ve")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot veSnapshot : snapshot.getChildren()) {
                            String tenPhim = veSnapshot.child("tenphim").getValue(String.class);
                            String phong = veSnapshot.child("phong").getValue(String.class);
                            String gio = veSnapshot.child("gio").getValue(String.class);

                            if (tenPhim.equals(movieName) && phong.equals(room) && gio.equals(time)) {
                                DataSnapshot gheSnapshot = veSnapshot.child("ghe");
                                for (DataSnapshot seatSnapshot : gheSnapshot.getChildren()) {
                                    String seat = seatSnapshot.getValue(String.class);
                                    seatStatusMap.put(seat, true); // Đặt trạng thái ghế là true (đã có người đặt)
                                }
                                // Sau khi tìm thấy vé phù hợp, thoát khỏi vòng lặp
                                break;
                            }
                        }
                        // Sau khi kiểm tra xong tất cả các vé, bạn có thể thực hiện các thao tác cần thiết với seatStatusMap ở đây
                        updateSeatButtons(seatStatusMap);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý khi có lỗi xảy ra
                    }
                });
    }



    private void updateSeatButtons(Map<String, Boolean> seatStatusMap) {
        // Duyệt qua tất cả các nút ghế và thiết lập trạng thái của chúng dựa trên seatStatusMap
        for (String seat : seatStatusMap.keySet()) {
            boolean isBooked = seatStatusMap.get(seat);
            // Tìm nút ghế tương ứng với seat
            Button seatButton = findSeatButtonBySeatName(seat);
            if (seatButton != null) {
                // Nếu ghế đã được đặt, vô hiệu hóa nút ghế
                seatButton.setEnabled(!isBooked);
                // Đặt màu sắc hoặc hiệu ứng khác tùy thuộc vào trạng thái của ghế
                if (isBooked) {
                    seatButton.setBackgroundTintList(getResources().getColorStateList(R.color.selected_seat_color)); // Ví dụ: đặt màu đỏ cho ghế đã đặt
                } else {
                    seatButton.setBackgroundTintList(getResources().getColorStateList(R.color.default_seat_color)); // Ví dụ: đặt màu đỏ cho ghế đã đặt
                }
            }
        }
    }

    private Button findSeatButtonBySeatName(String seat) {
        // Duyệt qua tất cả các nút ghế trên giao diện người dùng và so sánh tên ghế với seatName
        for (Button seatButton : seatButtonsList) { // Giả sử seatButtonsList là danh sách chứa tất cả các nút ghế
            if (seatButton.getText().toString().equals(seat)) {
                return seatButton; // Trả về nút ghế nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy
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
