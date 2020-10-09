package com.kplo.mapbab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextInputLayout TextInputLayout_Email, TextInputLayout_PW, TextInputLayout_Nick;
    private AppCompatEditText signup_Email, signup_PW, signup_Nick;
    private TextView signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        TextInputLayout_Email = (TextInputLayout) findViewById(R.id.TextInputLayout_Email);
        TextInputLayout_PW = (TextInputLayout) findViewById(R.id.TextInputLayout_PW);
        TextInputLayout_Nick = (TextInputLayout) findViewById(R.id.TextInputLayout_Nick);

        TextInputLayout_Email.setCounterEnabled(true);
        TextInputLayout_PW.setCounterEnabled(true);
        TextInputLayout_Nick.setCounterEnabled(true);

        signup_Email = (AppCompatEditText) findViewById(R.id.signup_Email);
        signup_PW = (AppCompatEditText) findViewById(R.id.signup_PW);
        signup_Nick = (AppCompatEditText) findViewById(R.id.signup_Nick);
        signup_btn = (TextView) findViewById(R.id.signup_btn);


        firebaseAuth = FirebaseAuth.getInstance();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!signup_Email.getText().toString().equals("") && !signup_PW.getText().toString().equals("")) {
                    // 이메일과 비밀번호가 공백이 아닌 경우
                    createUser(signup_Email.getText().toString(), signup_PW.getText().toString(), signup_Nick.getText().toString());
                } else {
                    // 이메일과 비밀번호가 공백인 경우
                    Toast.makeText(SignupActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void createUser(String email, String password, String name) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // 계정이 중복된 경우
                            Toast.makeText(SignupActivity.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
