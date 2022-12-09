package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class SingleHistory extends AppCompatActivity {

    private Button cancel;
    private TextView date_2, date_1, totalcost, number_of_rooms, hotel_status, hotel_place, hotel_name;
    private ImageView singleHimage;
    MaterialToolbar topAppbar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private RelativeLayout parent;



    private TextView testing;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_history);

        cancel = findViewById(R.id.cancel);
        singleHimage = findViewById(R.id.singleHimage);

        date_1 = findViewById(R.id.date_1);
        date_2 = findViewById(R.id.date_2);
        totalcost = findViewById(R.id.totalcost);
        number_of_rooms = findViewById(R.id.number_of_rooms);
        hotel_name = findViewById(R.id.hotel_name);
        hotel_status = findViewById(R.id.hotel_status);
        hotel_place = findViewById(R.id.hotel_place);
        parent = findViewById(R.id.parent);
        topAppbar = findViewById(R.id.topAppbar);



        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        topAppbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleHistory.this, user_history.class));
            }
        });


        Picasso.get().load(getIntent().getStringExtra("singlehotel_image"))
                .placeholder(R.drawable.ic_launcher_background)
                .into(singleHimage);

        date_1.setText(getIntent().getStringExtra("singledate_1"));
        date_2.setText(getIntent().getStringExtra("singledate_2"));
        totalcost.setText(getIntent().getStringExtra("singlehotel_price"));
        number_of_rooms.setText(getIntent().getStringExtra("singlenum_of_rooms"));
        hotel_name.setText(getIntent().getStringExtra("singlehotel_name"));
        hotel_place.setText(getIntent().getStringExtra("singlehotel_place"));
        hotel_status.setText(getIntent().getStringExtra("singlestatus"));
        topAppbar.setTitle(getIntent().getStringExtra("singlehotel_name"));

        String hotel_status = getIntent().getStringExtra("singlestatus");
        String UID = getIntent().getStringExtra("UID");
        String final_status = "Cancelled";


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Transactions").child(UID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = (String) snapshot.child("status").getValue();
                if(value.equals(final_status)){
                    cancel.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Transactions").child(UID);
                reference.child("status").setValue("Cancelled");







                        Snackbar.make(parent, "Your reservation has been cancelled", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SingleHistory.this, user_history.class));




            }
        });






    }
}