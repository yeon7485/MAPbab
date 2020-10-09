package com.kplo.mapbab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signup_btn, login_btn;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private TextInputLayout TextInputLayout_Login_Email, TextInputLayout_Login_PW;
    private AppCompatEditText login_Email, login_PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        TextInputLayout_Login_Email = (TextInputLayout) findViewById(R.id.TextInputLayout_Login_Email);
        TextInputLayout_Login_PW = (TextInputLayout) findViewById(R.id.TextInputLayout_Login_PW);
        TextInputLayout_Login_Email.setCounterEnabled(true);
        TextInputLayout_Login_PW.setCounterEnabled(true);

        login_Email = (AppCompatEditText) findViewById(R.id.login_Email);
        login_PW = (AppCompatEditText) findViewById(R.id.login_PW);
        signup_btn = findViewById(R.id.signup_btn);
        login_btn = findViewById(R.id.login_btn);

        signup_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);


        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish();
                } else { }
            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                if (!login_Email.getText().toString().equals("") && !login_PW.getText().toString().equals("")) {
                    loginUser(login_Email.getText().toString(), login_PW.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.signup_btn:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                break;
        }
    }
    public void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            firebaseAuth.addAuthStateListener(firebaseAuthListener);
                        } else {
                            // 로그인 실패
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}