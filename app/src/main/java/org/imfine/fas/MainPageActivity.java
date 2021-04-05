package org.imfine.fas;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import org.imfine.fas.Service.MyService;
import org.imfine.fas.data.FirebasePostFallAlert;


public class MainPageActivity extends AppCompatActivity {

    //데이터베이스에서 데이터를 읽으려면 DatabaseReference의 인스턴스가 필요.
    private DatabaseReference mDatabase;



    ImageView check, register, statistic, cctv, storage;
    Button btnrecord;
    TextView tv1, tv2, tv3;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        check = findViewById(R.id.main_page_ImgBtn_douricheck);
        register = findViewById(R.id.main_page_ImgBtn_douriregister);
        //statistic = findViewById(R.id.main_page_ImgBtn_douristatistics);
        cctv = findViewById(R.id.main_page_ImgBtn_cctv);
        storage = findViewById(R.id.main_page_ImgBtn_storage);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        tv1 = findViewById(R.id.main_page_tv_name);
        tv2 = findViewById(R.id.main_page_tv_value);
        tv3 = findViewById(R.id.main_page_tv_time);
        btnrecord = findViewById(R.id.main_page_btn_record);


        //record버튼 눌렀을 때
        btnrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DouriRecordActivity.class);
                startActivity(intent);
            }
        });

        //독거노인 확인 버튼 누르면 그 페이지로 이동
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DouriCheckingActivity.class);
                startActivity(intent);
            }
        });

        //독거노인 등록 버튼 누르면 그 페이지로 이동
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DouriRegisterActivity.class);
                startActivity(intent);
            }
        });

        //통계 버튼 누르면 그 페이지로 이동
//        statistic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainPageActivity.this, "준비중입니다.", Toast.LENGTH_SHORT).show();
//            }
//        });

        //실시간 cctv 누르면 그 페이지로 이동
        cctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DouriCCTVActivity.class);
                startActivity(intent);
            }
        });

        //storage버튼 누르면 그 페이지로 이동
        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DouriStorageActivity.class);
                startActivity(intent);
            }
        });


        //데이터베이스 불러오기
        mDatabase.child("alarm_list").addChildEventListener(new ChildEventListener() {
            //추가되었을 때
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebasePostFallAlert fa = dataSnapshot.getValue(FirebasePostFallAlert.class);
                tv3.setText(fa.time);
                tv1.setText(fa.name);
                tv2.setText(fa.value);
            }

            //수정되었을 때
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebasePostFallAlert fa = dataSnapshot.getValue(FirebasePostFallAlert.class);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    } //onCreate




    //뒤로 버튼 2번 누르면 종료되는 이벤트
    long first_time;
    long second_time;
    @Override
    public void onBackPressed() {
        second_time = System.currentTimeMillis();
        Toast.makeText(MainPageActivity.this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        if(second_time - first_time < 2000){
            super.onBackPressed();
            finishAffinity();
        }
        first_time = System.currentTimeMillis();
    }





    //로그아웃 누르면 로그아웃 하시겠습니까? 창 띄우는 이벤트
    public void Logout(View v){
        new AlertDialog.Builder(this)
                .setTitle("로그아웃").setMessage("로그아웃 하시면 더 이상 알림을 받으실 수 없습니다. 로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        //Intent intent = new Intent(MainPageActivity.this, LoginPageActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        //startActivity(intent);
                        ActivityCompat.finishAffinity(MainPageActivity.this);
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }


}// end of Activity
