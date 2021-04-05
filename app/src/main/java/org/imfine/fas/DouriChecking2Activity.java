package org.imfine.fas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DouriChecking2Activity extends AppCompatActivity {

    Intent intent;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    ImageView imageView;
    ImageView backBtn;

    //데이터베이스에서 데이터를 읽으려면 DatabaseReference의 인스턴스가 필요.
    private DatabaseReference mDatabase;

    //storage에서 데이터를 읽으려면 인스턴스 필요
    //private StorageReference mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_checking2);

        intent = getIntent();
        final String uid = intent.getStringExtra("uid");


        textView1 = findViewById(R.id.douri_checking2_TV_name);
        textView2 = findViewById(R.id.douri_checking2_TV_age);
        textView3 = findViewById(R.id.douri_checking2_TV_sex);
        textView4 = findViewById(R.id.douri_checking2_TV_addr);
        textView5 = findViewById(R.id.douri_checking2_TV_phone);
        textView6 = findViewById(R.id.douri_checking2_TV_history);
        textView7 = findViewById(R.id.douri_checking2_TV_wb);
        textView8 = findViewById(R.id.douri_checking2_TV_cam);
        imageView = findViewById(R.id.douri_checking2_IV_img);

        //back버튼
        backBtn = findViewById(R.id.douri_checking2_IV_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DouriChecking2Activity.this, DouriCheckingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //mStorage = FirebaseStorage.getInstance().getReference();
        Glide.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/fas-imfine.appspot.com/o/userImage%2F"+uid+"?alt=media")
                .into(imageView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("oldman").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebasePostDouriReg get = dataSnapshot.getValue(FirebasePostDouriReg.class);
                textView1.setText("이름 : " + get.name);
                textView2.setText("나이 : " + get.age);
                textView3.setText("성별 : " + get.sex);
                textView4.setText("주소 : " + get.addr);
                textView5.setText("핸드폰 : " + get.phone);
                textView6.setText("병력 : " + get.history);
                textView7.setText("웨어러블밴드: " + get.wb);
                textView8.setText("카메라별칭: " + get.cam);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    } //end of onCreate







}
