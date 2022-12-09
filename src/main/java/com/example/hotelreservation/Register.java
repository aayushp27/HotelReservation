package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";

    private EditText user_full_name, user_email, user_password, contact;
    private Button RegisterBtn;
    private TextView regSignIN;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private Toolbar toolbar;



    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_full_name = findViewById(R.id.user_full_name);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        contact = findViewById(R.id.contact);
        RegisterBtn = findViewById(R.id.RegisterButton);
        regSignIN = findViewById(R.id.regSignIN);
        toolbar = findViewById(R.id.register_toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();





        regSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Ruserfull_name = user_full_name.getText().toString().trim();
                String Ruseremail = user_email.getText().toString().trim();
                String Ruserpassword = user_password.getText().toString().trim();
                String contact1= contact.getText().toString().trim();

                if(!(Ruserfull_name.isEmpty() && Ruseremail.isEmpty() && Ruserpassword.isEmpty() && contact1.isEmpty()))
                {
                    //all fields filled

                        if (Ruserpassword.length() > 6)
                        {
                            //password greater than 6 characters.

                            firebaseAuth.createUserWithEmailAndPassword(Ruseremail, Ruserpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //User Created
                                        String UID = task.getResult().getUser().getUid();



                                        Users user = new Users(UID, Ruserfull_name, Ruseremail, contact1, Ruserpassword, 0);

                                        firebaseDatabase.getReference().child("Users").child(UID).setValue(user);

                                        Toast.makeText(Register.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Failed
                                    Toast.makeText(Register.this, "Error: "+ e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    Toast.makeText(Register.this, "Cancelled try again", Toast.LENGTH_SHORT).show();

                                }
                            })

                            ;


                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
                        }



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter all the details", Toast.LENGTH_SHORT).show();
                }




                }

        });

    }


}

