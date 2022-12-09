package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_profile extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private TextView full_name, email, contact;
    private FloatingActionButton fab;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference user_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.userPtoolbar);
        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        fab = findViewById(R.id.fab);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        String UID = firebaseAuth.getCurrentUser().getUid();

        user_ref = firebaseDatabase.getReference("Users");


        user_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("uid").getValue().equals(UID)){

                       full_name.setText(ds.child("full_name").getValue(String.class));
                       email.setText(ds.child("user_email").getValue(String.class));
                      contact.setText(ds.child("contact").getValue(String.class));

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_profile.this, Profile_edit_USER.class));
            }
        });










        bottomNavigationView = findViewById(R.id.userBottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                                                           @Override
                                                           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                               switch (item.getItemId())
                                                               {
                                                                   case R.id.history:
                                                                       startActivity(new Intent(getApplicationContext(), user_history.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                   case R.id.home:
                                                                       startActivity(new Intent(getApplicationContext(), User_homepage.class));
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