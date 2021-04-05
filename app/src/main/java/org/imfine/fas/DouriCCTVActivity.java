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
import org.imfine.fas.adapter.OldmanAdapter;
import org.imfine.fas.data.Oldman;

import java.util.ArrayList;

public class DouriCCTVActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    RecyclerView rv;
    CCTVAdapter adapter;
    ImageView backBtn;
    ArrayList<Oldman> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_cctv);

        //back버튼
        backBtn = findViewById(R.id.douri_cctv_IV_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DouriCCTVActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rv = findViewById(R.id.douri_cctv_rv);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setData();

    } //end of oncreate

    private void setData() {
        mDatabase.child("oldman").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Oldman data = ds.getValue(Oldman.class);
                    data.setId(ds.getKey());
                    datas.add(data);
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
        adapter = new CCTVAdapter(this, datas);
        rv.setAdapter(adapter);

    }

}
