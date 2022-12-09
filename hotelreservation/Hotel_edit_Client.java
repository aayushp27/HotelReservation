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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Hotel_edit_Client extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    Button cancel, save_changes;
    EditText edtcontact, edtprice, edtdescription, edthotelmail;
    TextView hotel_name, hotel_place;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference user_ref, user_ref1;


    private static String old_pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_edit_client);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        cancel = findViewById(R.id.cancel);
        save_changes = findViewById(R.id.save_changes);
        edtcontact = findViewById(R.id.edtcontact);
        edtdescription = findViewById(R.id.edtdescription);
        edthotelmail = findViewById(R.id.edthotelmail);
        edtprice = findViewById(R.id.edtprice);
        hotel_name = findViewById(R.id.hotel_name);
        hotel_place = findViewById(R.id.hotel_place);




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Hotel_edit_Client.this, client_hotel.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        String client_email = firebaseAuth.getCurrentUser().getEmail();

        user_ref = firebaseDatabase.getReference("Hotels");


        user_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("owner_email").getValue().equals(client_email)){


                        edtcontact.setText(ds.child("hotel_phone").getValue(String.class));
                        hotel_name.setText(ds.child("hotel_name").getValue(String.class));
                        edtdescription.setText(ds.child("hotel_desc").getValue(String.class));
                        edthotelmail.setText(ds.child("hotel_email").getValue(String.class));
                        hotel_place.setText(ds.child("hotel_place").getValue(String.class));
                        edtprice.setText(ds.child("hotel_price").getValue(String.class));

                        startActivity(new Intent(Hotel_edit_Client.this, client_hotel.class));
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

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Hotels").child(hotel_name.getText().toString());
                reference.child("hotel_email").setValue(edthotelmail.getText().toString().trim());
                reference.child("hotel_phone").setValue(edtcontact.getText().toString().trim());
                reference.child("hotel_price").setValue(edtprice.getText().toString().trim());
                reference.child("hotel_desc").setValue(edtdescription.getText().toString().trim());



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
                                                                       startActivity(new Intent(Hotel_edit_Client.this, Client_homepage.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;

                                                                   case R.id.profile:
                                                                       startActivity(new Intent(Hotel_edit_Client.this, client_profile.class));
                                                                       overridePendingTransition(0,0);
                                                                       return true;
                                                               }


                                                               return false;
                                                           }

                                                       }
        );

    }
}