package org.imfine.fas;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                //Intent intent = new Intent (getApplicationContext(), LoginPageActivity.class);
                //startActivity(intent); //다음화면으로 넘어감
                //finish();
                setPermission();
            }
        },2000); //3초 뒤에 Runner객체 실행하도록 함
    }

    private void setPermission() {
        /*
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.CALL_PHONE"/>
         */
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("권한에 동의하지 않으시면 앱을 사용하실 수 없습니다.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE)
                .check();
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //Toast.makeText(IntroActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent (getApplicationContext(), LoginPageActivity.class);
            startActivity(intent); //다음화면으로 넘어감
            finish();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            //Toast.makeText(IntroActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            finish();
        }


    };

}
