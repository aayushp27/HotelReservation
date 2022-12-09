package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText login_pass;
    private EditText login_email;
    private Button LoginBtn;
    private TextView regSignIN;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_pass = findViewById(R.id.login_pass);
        LoginBtn = findViewById(R.id.LoginButton);
        regSignIN = findViewById(R.id.regSignIN);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        regSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }

        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = login_email.getText().toString().trim();
                String password = login_pass.getText().toString().trim();

               if(!email.isEmpty() && !password.isEmpty())
               {
                    //no empty fields
                   firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful())
                           {

                               String UID = task.getResult().getUser().getUid();

                               firebaseDatabase.getReference().child("Users").child(UID).child("user_type").addListenerForSingleValueEvent(new ValueEventListener() {
                                   @Override
                                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                                       int user_type = snapshot.getValue(int.class);


                                       if(user_type == 0)
                                       {
                                           startActivity(new Intent(Login.this, User_homepage.class));
                                       }
                                       else{
                                           startActivity(new Intent(Login.this, Client_homepage.class));
                                       }
                                       finish();

                                   }

                                   @Override
                                   public void onCancelled(@NonNull DatabaseError error) {

                                   }
                               });


                           }

                           else {
                               Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                           }
                       }
                   }
                   );




               }
               else {
                   Toast.makeText(getApplicationContext(),"Please enter all the details", Toast.LENGTH_SHORT).show();

               }




                }
            }
        );




    }
}