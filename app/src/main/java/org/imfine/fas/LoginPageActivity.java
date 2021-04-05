package org.imfine.fas;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.imfine.fas.data.User;

import java.util.regex.Pattern;



public class LoginPageActivity extends AppCompatActivity {


    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    FirebaseAuth mAuth;
    FirebaseUser fUser;
    User mUser;


    // 이메일과 비밀번호 별명받을 객체
    private EditText editTextEmail;
    private EditText editTextPassword;

    //변수 선언
    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        /*
        //회원가입 버튼 누르면 회원가입 창으로 넘어가는 이벤트
        ImageButton login_page_Button_signup = (ImageButton)findViewById(R.id.login_page_ImgBtn_signup);
        login_page_Button_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        */

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        if(fUser!=null){
            Intent intent = new Intent(LoginPageActivity.this, MainPageActivity.class);
            startActivity(intent);
            finish();
        }

        editTextEmail = (EditText) findViewById(R.id.login_page_ET_email);
        editTextPassword = (EditText)findViewById(R.id.login_page_ET_pw);

    } // end of onCreate

    public void signUp(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if(isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    public void signIn(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if(isValidEmail() && isValidPasswd()) {
            loginUser(email, password);
        }
    }

    // 이메일 유효성 검사
    private boolean isValidEmail() {
        if (email.isEmpty()) {
            // 이메일 공백
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            // 비밀번호 공백
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
             //비밀번호 형식 불일치
             return false;
        } else {
            return true;
        }
    }

    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            Toast.makeText(LoginPageActivity.this, "회원가입 성공. 다시 로그인 해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            // 회원가입 실패
                            Toast.makeText(LoginPageActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 로그인
    private void loginUser(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(LoginPageActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), MainPageActivity.class);
                            startActivity(intent);

                        } else {
                            // 로그인 실패
                            Toast.makeText(LoginPageActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}//end of Activity
