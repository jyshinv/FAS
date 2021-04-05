package org.imfine.fas;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FMS";


    public MyFirebaseMessagingService() {

    }


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.e(TAG, "onNewToken 호출됨 " +token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG,"onMessageReceived() 호출됨.");

        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        String contents = data.get("contents");
        String contents2 = "낙상 의심";
        Log.d(TAG,"from : " + from + " contents :" + contents);


        //contents가 null이면 낙상 확정 알람창
        //그렇지 않으면 낙상 알림 알림창
        if(TextUtils.isEmpty(contents)) {
            sendToActivity2(getApplicationContext());
        }else {
            sendToActivity(getApplicationContext(),from,contents);

        }
    }

    private void sendToActivity(Context context, String from, String contents){
        Intent intent = new Intent(context, FCMReciveTestActivity.class);
        //intent.putExtra("from",from);
        //intent.putExtra("contents",contents);

        //액티비티 쪽으로 데이터를 보내기 위해 인텐트 객체를 만들고 startActivity() 메서드 호출
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private void sendToActivity2(Context context){
        Intent intent = new Intent(context, FCMReciveFromRaspActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }






}
