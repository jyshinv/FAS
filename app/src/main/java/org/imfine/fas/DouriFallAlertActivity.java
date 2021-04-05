package org.imfine.fas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DouriFallAlertActivity extends AppCompatActivity {

    Intent intent;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_fall_alert);

        tv1 = findViewById(R.id.fa_tv_time);
        tv2 = findViewById(R.id.fa_tv_name);
        tv3 = findViewById(R.id.fa_tv_value);
        btn = findViewById(R.id.fa_btn_ok);

        intent = getIntent();
        final String time = intent.getStringExtra("time");
        final String name = intent.getStringExtra("name");
        final String value = intent.getStringExtra("value");

        tv1.setText(time);
        tv2.setText(name);
        tv3.setText(value);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
