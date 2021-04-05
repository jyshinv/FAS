package org.imfine.fas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SignUpActivity extends AppCompatActivity {

    /*private DatabaseReference mPostReference;
    String Id;
    String Pw;
    String PwCheck;
    String Name;
    String sort = "id"; //sort ex) 저장된 후 핸드폰 화면에 listview 로 나타낼 때  id1: 이름(성별, 나이)로 분류된다.
    EditText idET, pwET, pwcheckET, nameET;
    Button sign_up_button_ok;
    //ListView listView;
    ArrayList<String> signupdata;
    //ArrayAdapter<String> signuparrayAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /*
        //취소 버튼 누르면 로그인 페이지로 이동하기
        final Button sign_up_button_cancel = (Button)findViewById(R.id.sign_up_Btn_cancel);
        sign_up_button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginPageActivity.class);
                startActivity(intent);
            }
        });

        //firebase db 연동을 위한 코드 시작
        signupdata = new ArrayList<String>();
        idET = (EditText)findViewById(R.id.sign_up_ET_id);
        pwET = (EditText)findViewById(R.id.sign_up_ET_pw);
        pwcheckET =(EditText)findViewById(R.id.sign_up_ET_pwcheck);
        nameET = (EditText)findViewById(R.id.sign_up_ET_name);
        sign_up_button_ok = (Button)findViewById(R.id.sign_up_Button_ok);
        //listView = (ListView)findViewById(R.id.datalist);

        mPostReference = FirebaseDatabase.getInstance().getReference();

        sign_up_button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Id = idET.getText().toString();
                Pw = pwET.getText().toString();
                PwCheck = pwcheckET.getText().toString();
                Name = nameET.getText().toString();

                if ((Id.length() * Pw.length() * PwCheck.length() * Name.length()) == 0) {
                    Toast.makeText(SignUpActivity.this, "모두 입력해주세요", Toast.LENGTH_SHORT).show();
                }else if(!Pw.equals(PwCheck)){
                    Toast.makeText(SignUpActivity.this, "비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignUpActivity.this,"회원가입 완료", Toast.LENGTH_SHORT).show();
                    postFirebaseDatabase(true);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(),LoginPageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },2000);
                }
            }
        });

        //signuparrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //listView.setAdapter(arrayAdapter);
        //getFirebaseDatabase();
        */

    } // end of OnCreate


    /*public void getFirebaseDatabase() {

        final ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("onDataChange", "Data is Updated");
                signupdata.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FirebasePostSignUp get = postSnapshot.getValue(FirebasePostSignUp.class);
                    String[] info = {get.id, get.pw, get.pwcheck, get.name};
                    String result = info[0] + " : " + info[1] + info[2] +  "(" + info[3] + ")";
                    signupdata.add(result);
                    Log.d("getFirebaseDatabase", "key: " + key);
                    Log.d("getFirebaseDatabase", "info: " + info[0] + info[1] + info[2] + info[3]);
                }
                signuparrayAdapter.clear();
                signuparrayAdapter.addAll(signupdata);
                signuparrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mPostReference.child("douri_id_list").addValueEventListener(postListener);
    }*/

    /*
    public void postFirebaseDatabase(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            FirebasePostSignUp post = new FirebasePostSignUp(Id, Pw, PwCheck, Name);
            postValues = post.toMap();
        }
        childUpdates.put("/douri_id_list/" + Id, postValues);
        mPostReference.updateChildren(childUpdates);
        clearET();
    }

    public void clearET () {
        nameET.setText("");
        pwcheckET.setText("");
        pwET.setText("");
        idET.setText("");
        Name = "";
        PwCheck = "";
        Pw = "";
        Id = "";
    }
    */

}
