package org.imfine.fas;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.imfine.fas.data.User;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DouriRegisterActivity extends AppCompatActivity {

    //Class variables
    private DatabaseReference douriPostReference;

    //독거노인 정보 입력받을 변수
    String Name, Age, Sex, Addr, Phone, History, Wb, Cam;

    //et, iv, imageuri
    EditText nameET, ageET, sexET, addrET, phoneET, historyET, wbET, camET;
    ImageView cancelBtn;
    ImageView okBtn;
    ImageView backBtn;
    ImageView imgView;
    private Uri ImageUri = null;


    //ArrayList<String> regdata;

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douri_register);



        //취소 버튼 누르면 메인 페이지로 이동
        cancelBtn = findViewById(R.id.douri_reg_Btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clearET(); --> 에러남 취소누르고 다시 들어가면 edit 지워져 있음
                Intent intent = new Intent(getApplication(), MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        }); // end of cancelBtn

        //back버튼
        backBtn = findViewById(R.id.douri_reg_IV_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DouriRegisterActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //firebase db 연동을 위한 코드 시작
        //regdata = new ArrayList<String>();
        nameET = (EditText)findViewById(R.id.douri_reg_ET_name);
        ageET = (EditText)findViewById(R.id.douri_reg_ET_age);
        sexET = (EditText)findViewById(R.id.douri_reg_ET_sex);
        addrET = (EditText)findViewById(R.id.douri_reg_ET_addr);
        phoneET = (EditText)findViewById(R.id.douri_reg_ET_phone);
        historyET = (EditText)findViewById(R.id.douri_reg_ET_history);
        wbET = (EditText)findViewById(R.id.douri_reg_ET_wb);
        camET = (EditText)findViewById(R.id.douri_reg_ET_cam);
        okBtn = (ImageView)findViewById(R.id.douri_reg_Btn_ok);
        imgView = (ImageView)findViewById(R.id.douri_reg_ImaBtn_img);

        //By using this object, you can access to realtime DB
        douriPostReference = FirebaseDatabase.getInstance().getReference();

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(DouriRegisterActivity.this).single().includeVideo(false).start();
            }
        });


        //확인 버튼을 누르면
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name= nameET.getText().toString();
                Age= ageET.getText().toString();
                Sex= sexET.getText().toString();
                Addr= addrET.getText().toString();
                Phone= phoneET.getText().toString();
                History= historyET.getText().toString();
                Wb= wbET.getText().toString();
                Cam= camET.getText().toString();

                if((Name.length()*Age.length()*Sex.length()*Addr.length()*Phone.length()) == 0){
                    Toast.makeText(DouriRegisterActivity.this, "(*)는 필수입력입니다.", Toast.LENGTH_SHORT).show();
                }else if(ImageUri == null){
                    Toast.makeText(DouriRegisterActivity.this, "사진을 등록해 주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DouriRegisterActivity.this, "등록중입니다...", Toast.LENGTH_LONG).show();
                    UploadImage();
                    //postFirebaseDatabase();
                }


            }
        }); //end of okBtn

    }//end of onCreate

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            Glide.with(this).load(image.getPath()).into(imgView);
            ImageUri = Uri.fromFile(new File(image.getPath()));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void UploadImage(){
        String key = douriPostReference.child("oldman").push().getKey();
//        이미지 없어도 등록 될 수 있게 만드는 코드
//        if(ImageUri == null){
//            postFirebaseDatabase(key);
//        }

        StorageReference mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("userImage").child(key).putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                postFirebaseDatabase(key);
            }
        });
    }

    public void postFirebaseDatabase(String key){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;

        FirebasePostDouriReg post = new FirebasePostDouriReg(Name, Age, Sex, Addr, Phone, History, Wb, Cam);
        postValues = post.toMap();


        //key값 말고 서버에서 구별해서(ex)band 별명으로!!) 조회할 수 있는 값으로 등록하기
        childUpdates.put("/oldman/" + key ,postValues);
        douriPostReference.updateChildren(childUpdates);
        clearET();
        Toast.makeText(DouriRegisterActivity.this, "등록완료", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DouriRegisterActivity.this, MainPageActivity.class);
        startActivity(intent);
        finish();


    } // end of postFirebaseDatabase

    //EditText에 남은 글자 지워주는 함수
    public void clearET(){
        camET.setText("");
        wbET.setText("");
        historyET.setText("");
        phoneET.setText("");
        addrET.setText("");
        sexET.setText("");
        ageET.setText("");
        nameET.setText("");
        Cam = "";
        Wb = "";
        History = "";
        Phone = "";
        Addr = "";
        Sex = "";
        Age = "";
        Name = "";

    } // end of clearET

}//end of Activity
