package org.imfine.fas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imfine.fas.adapter.CCTVAdapter;
import org.imfine.fas.adapter.RecordAdapter;
import org.imfine.fas.data.Accident;
import org.imfine.fas.data.Oldman;

import java.util.ArrayList;

public class DouriRecordActivity extends AppCompatActivity {


    //데이터베이스에서 데이터를 읽으려면 DatabaseReference의 인스턴스가 필요.
    private DatabaseReference mDatabase;

    RecyclerView rv;
    RecordAdapter adapter;
    ImageView backBtn;
    ArrayList<Accident> datas2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_record);

        //back버튼
        backBtn = findViewById(R.id.douri_record_IV_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DouriRecordActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        rv = findViewById(R.id.douri_record_rv);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setData();
    }




    private void setData() {
        mDatabase.child("alarm_list").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Accident data = ds.getValue(Accident.class);
                    data.setKey(ds.getKey());
                    datas2.add(data);
                }
                setRv();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setRv() {

        RecyclerView.LayoutManager lm
                = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); //일렬, 세로
        rv.setLayoutManager(lm);
        adapter = new RecordAdapter(this, datas2);
        rv.setAdapter(adapter);

    }

}
