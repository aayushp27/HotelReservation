package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.ValueEventListener;

public class client_hotel extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar client_toolbar;
    private TextView hotel_name, hotel_place, contact, hotel_price, hotel_desc, hotel_email;
    private FloatingActionButton fab;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference user_ref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_hotel);

        client_toolbar = findViewById(R.id.client_toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        contact = findViewById(R.id.contact);
        hotel_name = findViewById(R.id.hotel_name);
        hotel_email = findViewById(R.id.hotel_email);
        hotel_desc = findViewById(R.id.hotel_desc);
        hotel_place = findViewById(R.id.hotel_place);
        hotel_price = findViewById(R.id.hotel_price);
        fab = findViewById(R.id.fab);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        String client_email = firebaseAuth.getCurrentUser().getEmail();

        user_ref = firebaseDatabase.getReference("Hotels");


        user_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("owner_email").getValue().equals(client_email)){


                        contact.setText(ds.child("hotel_phone").getValue(String.class));
                        hotel_name.setText(ds.child("hotel_name").getValue(String.class));
                        hotel_desc.setText(ds.child("hotel_desc").getValue(String.class));
                        hotel_email.setText(ds.child("hotel_email").getValue(String.class));
                        hotel_place.setText(ds.child("hotel_place").getValue(String.class));
                        hotel_price.setText(ds.child("hotel_price").getValue(String.class));

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
                 startActivity(new Intent(client_hotel.this, Hotel_edit_Client.class));
                 }
        });


        bottomNavigationView.setSelectedItemId(R.id.hotel);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.hotel:
                        return true;


                    case R.id.history:
                        startActivity(new Intent(client_hotel.this, Client_homepage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(client_hotel.this, client_profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }


                return false;
            }

        });
    }
}