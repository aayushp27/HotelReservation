package com.example.hotelreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText mFullName, mEmail, mPassword, mConfirmPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.FullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.Pass);
        mConfirmPassword = findViewById(R.id.Confirm_Pass);
        mRegisterBtn = findViewById(R.id.RegisterButton);
        mLoginBtn = findViewById(R.id.CreateText);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();
                final String FullName = mFullName.getText().toString().trim();
                String Confirm_Password = mConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(Password)){
                    mPassword.setError("Password is required.");
                    return;
                }
                if (Password.length() < 6){
                    mPassword.setError("Password must be > = 6 characters");
                    return;
                }

        }

        });


    }
}