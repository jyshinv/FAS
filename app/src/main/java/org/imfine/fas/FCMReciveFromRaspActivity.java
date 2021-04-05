package org.imfine.fas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.imfine.fas.data.FirebasePostFallAlert;
import org.imfine.fas.data.FirebaseRaspPost;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FCMReciveFromRaspActivity extends AppCompatActivity {

    Button btn;
    TextView tv1;
    TextView tv2;
    Button btn2;
    Intent intent;

    //현재 날짜와 시간을 위한 변수
    long now;
    private DecimalFormat df = new DecimalFormat("#.###");

    //파이어베이스 db연동을 위한 코드
    private DatabaseReference mPostReference;
    String FALLCHECK = " ";
    String TIME;
    String NAME;
    String VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmrecive_from_rasp);

        //fb에 보낼 데이터 값
        now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfnow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TIME = "낙상 시간 : "+sdfnow.format(date);
        NAME = "낙상인 : 김옥례 할머니";
        VALUE = "낙상 확정";

        //파이어베이스
        mPostReference = FirebaseDatabase.getInstance().getReference();
        FBRegister(TIME, NAME, VALUE);

        //sms 보내기
        String phone = "01058718889";
        String sms ="김옥례 할머니가 위급합니다.";
        Toast.makeText(getApplicationContext(), "SMS 전송완료", Toast.LENGTH_SHORT).show();

        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("01058718889",null,"김옥례 할머니 위급!!",null,null);
        }catch (Exception e){
            //Toast.makeText(getApplicationContext(), "SMS Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        //btn = findViewById(R.id.fcm_btn);
        //인스턴스id 확인 버튼

        //tv1 =findViewById(R.id.fcm_tv1);
        //tv2 = findViewById(R.id.fcm_tv2);

        //진동
        //Vibrator vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        //vib.vibrate(new long[]{500, 1000, 500, 1000},-1);

        //소리 알림
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringtone.play();

        //cctv버튼 누르면 cctv화면으로 이동
        btn2 = findViewById(R.id.fcmreceivefromrasp_btn_cctvbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FALLCHECK = "false";
                FBtoRasp();

                //원래코드 :cctv화면으로 이동
                //intent = new Intent(FCMReciveFromRaspActivity.this, DouriCCTV2Activity.class);
                //intent.putExtra("uid","-Lo40k9AiDa3tTMU_PZh");
                //startActivity(intent);
                //finish();

                //임시코드
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://192.168.35.72:8080/?action=stream"));
                startActivity(intent);
                finish();
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,
                new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult result) {
                        String newToken = result.getToken();
                        //println("등록 id : " +newToken);
                        Log.d("등록 id: ", newToken);

                    }
                });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String instanceId = FirebaseInstanceId.getInstance().getId();
//                println("확인된 인스턴스 id : " +instanceId);
//            }
//        });




    }//end of oncreate

    //낙상 정보 db에 기록하기(낙상 확정)
    public void FBRegister(String TIME, String NAME, String VALUE){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;

        FirebasePostFallAlert post = new FirebasePostFallAlert(TIME,NAME,VALUE);
        postValues = post.toMap();

        childUpdates.put("/alarm_list/"+ TIME, postValues);
        mPostReference.updateChildren(childUpdates);

        //Toast.makeText(this,"update completed",Toast.LENGTH_SHORT).show();

    }



    //핸드폰 백버튼 사용할 시에도 값 false로 바꾸기
    @Override
    public void onBackPressed() {
        FALLCHECK = "false";
        FBtoRasp();
        super.onBackPressed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //println("onNewIntent 호출됨");

        if(intent != null){
            processIntent(intent);
        }

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        String from = intent.getStringExtra("from");
        if(from == null){
            //println("from is null");
            return;
        }

        String contents = intent.getStringExtra("contents");

        //println("DATA : " +from +", "+contents);
        //tv1.setText("[" + from + "]로부터 수신한 데이터 : " +contents);
    }

    public void FBtoRasp() {
        mPostReference = FirebaseDatabase.getInstance().getReference();
        Map<String,Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;

        FirebaseRaspPost post = new FirebaseRaspPost(FALLCHECK);
        postValues = post.toMap();

        childUpdates.put("/FallCheckToRasp/", postValues);
        mPostReference.updateChildren(childUpdates);
    }
} // end of activity
