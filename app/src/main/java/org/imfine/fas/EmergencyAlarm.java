package org.imfine.fas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmergencyAlarm extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page); //main의 버튼

//        NotificationCompat.Builder mBuilder
//                =new NotificationCompat.Builder(EmergencyAlarm.this)
//                .setSmallIcon(R.drawable.newdourilogo)
//                .setContentTitle("알림제목!!")
//                .setContentText("알림내용!!");
//
//        btn = findViewById(R.id.main_page_btn_push);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap mLargeIconForNoti =
                        BitmapFactory.decodeResource(getResources(), R.drawable.newdourilogo);
                PendingIntent mPendingIntent =PendingIntent.getActivity(MainPageActivity.class, 0,
                        new Intent(getApplicationContext(), MainPageActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );



            }
        });*/



    }//end of oncreate
}//end of activity
