package org.imfine.fas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.imfine.fas.adapter.StorageAdapter;
import org.imfine.fas.data.FirebasePostFallAlert;
import org.imfine.fas.data.Oldman;
import org.imfine.fas.data.Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class DouriStorage2Activity extends AppCompatActivity {

    ImageView backBtn;
    TextView tv;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;


    //데이터베이스에서 데이터를 읽으려면 DatabaseReference의 인스턴스가 필요.
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_storage2);

        tv = findViewById(R.id.oldman_storage_tv);
        layout1 = findViewById(R.id.url1);
        layout2 = findViewById(R.id.url2);
        layout3 = findViewById(R.id.url3);


        //back버튼
        backBtn = findViewById(R.id.douri_st_IV_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/fas-imfine.appspot.com/o/videos%2F20191113_170329.mpeg?alt=media&token=9be243d4-fae5-411b-81fe-921216921215"));
                startActivity(intent);
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/fas-imfine.appspot.com/o/videos%2F20191113_170453.mpeg?alt=media&token=b368d269-b9ed-40ac-9ad9-a3d46d84d864"));
                startActivity(intent);

            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/fas-imfine.appspot.com/o/videos%2F20191113_170453.mpeg?alt=media&token=b368d269-b9ed-40ac-9ad9-a3d46d84d864"));
                startActivity(intent);

            }
        });






    } //onCreate()





}
