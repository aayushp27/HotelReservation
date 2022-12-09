package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_edit_Client extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    Button cancel, save_changes;
    EditText edtcontact, edtpassword;
    TextView full_name, email;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference user_ref, user_ref1;


    private static String old_pass;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_client);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        edtcontact = findViewById(R.id.edtcontact);
        edtpassword = findViewById(R.id.edtpassword);
        cancel = findViewById(R.id.cancel);
        save_changes = findViewById(R.id.save_changes);


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        String UID = firebaseAuth.getCurrentUser().getUid();

        user_ref1 = firebaseDatabase.getReference("Users");

        user_ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("uid").getValue().equals(UID)){
                        edtcontact.setText(ds.child("contact").getValue(String.class));
                        full_name.setText(ds.child("full_name").getValue(String.class));
                        edtpassword.setText(ds.child("user_password").getValue(String.class));
                        email.setText(ds.child("user_email").getValue(String.class).toString().trim());
                        old_pass = ds.child("user_password").getValue(String.class);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String client_email = firebaseAuth.getCurrentUser().getEmail();

        user_ref = firebaseDatabase.getReference("Hotels");

        user_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("owner_email").getValue().equals(client_email)){



                        edtcontact.setText(ds.child("contact").getValue(String.class));


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(UID);
                reference.child("contact").setValue(edtcontact.getText().toString().trim());

                user = FirebaseAuth.getInstance().getCurrentUser();
                final  String mail = user.getEmail();

                AuthCredential credential = EmailAuthProvider.getCredential(mail, old_pass);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            user.updatePassword(edtpassword.getText().toString());

                        }
                    }

                });

                reference.child("user_password").setValue(edtpassword.getText().toString().trim());
                startActivity(new Intent(Profile_edit_Client.this, client_profile.class));
            }
        });

















        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile_edit_Client.this, client_profile.class));
            }
        });








        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                                                           @Override
                                                           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                               switch (item.getItemId())
                                                               {
                                                                   case R.id.hotel:
                                                                       startActivity(new Intent(Profile_edit_Client.this, client_hotel.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                   case R.id.history:
                                                                       startActivity(new Intent(Profile_edit_Client.this, Client_homepage.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                   case R.id.profile:
                                                                       return true;
                                                               }


                                                               return false;
                                                           }

                                                       }
        );
    }
}