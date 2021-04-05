package org.imfine.fas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DouriCCTV2Activity extends AppCompatActivity {

    ImageView backbtn;
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



    //데이터베이스에서 데이터를 읽으려면 DatabaseReference의 인스턴스가 필요.
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_cctv2);

        intent = getIntent();
        final String uid = intent.getStringExtra("uid");


        textView1 = findViewById(R.id.douri_st_TV_name);
        textView2 = findViewById(R.id.douri_st_TV_age);
        textView3 = findViewById(R.id.douri_st_TV_sex);
        textView4 = findViewById(R.id.douri_st_TV_addr);
        textView5 = findViewById(R.id.douri_st_TV_phone);
        textView6 = findViewById(R.id.douri_st_TV_history);
        textView7 = findViewById(R.id.douri_st_TV_wb);
        textView8 = findViewById(R.id.douri_st_TV_cam);


        //back버튼
        backbtn = findViewById(R.id.douri_cctv2_IV_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DouriCCTV2Activity.this, DouriCCTVActivity.class);
                startActivity(intent);
                finish();
            }
        });

        WebView webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.setBackgroundColor(255);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //영상을 폭을 꽉 차게 하기 위해 직접 html태그로 작성함.
        webView.loadData("<html><head><style type='text/css'>" +
                "body{margin:auto auto;text-align:center;} " +
                "img{width:100%25;} div{overflow: hidden;} " +
                "</style></head><body><div><img src='http://192.168.35.72:8080/?action=stream'/></div></body></html>"
                ,"text/html", "UTF-8");

        //http://192.168.35.72:8080/?action=stream
        //"<html><head><style type='text/css'>body{margin:auto auto;text-align:center;} img{width:100%25;} div{overflow: hidden;} </style></head><body><div><img src='http://raspberrypi-ip:8080/stream/video.mjpeg'/></div></body></html>" ,"text/html",  "UTF-8"

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




    } // end of Oncreate
} //end of activity
