package com.example.hotelreservation;

import static android.R.layout.simple_spinner_item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SingleHotel extends AppCompatActivity {

    TextView singleHname, singleHplace, singleHdesc, singleHphone, singleHpincode, singleHcity, singleHstreet, singleHprice;
    ImageView singleHimage;


    private DatePickerDialog picker;
    Button book;
    TextView display_date_1, display_date_2;
    ImageView date_1, date_2;
    Spinner spinner;
    String from_date, to_date, number_of_rooms;
    String[] number = { "1", "2", "3"};
    int rooms_booked, number_of_day;

    RelativeLayout parent;



    TextView testing;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private Toolbar topAppbar;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_hotel);

        singleHname = findViewById(R.id.singleHname);
        singleHplace = findViewById(R.id.singleHplace);
        singleHdesc = findViewById(R.id.singleHdesc);
        singleHphone = findViewById(R.id.singleHphone);
        singleHpincode = findViewById(R.id.singleHpincode);
        singleHcity = findViewById(R.id.singleHcity);
        singleHstreet = findViewById(R.id.singleHstreet);
        singleHprice = findViewById(R.id.singleHprice);
        singleHimage = findViewById(R.id.singleHimage);
        display_date_1 = findViewById(R.id.display_date_1);
        date_1 = findViewById(R.id.date_1);
        display_date_2 = findViewById(R.id.display_date_2);
        date_2 = findViewById(R.id.date_2);
        book = findViewById(R.id.book);
        spinner = findViewById(R.id.spinner);
        testing = findViewById(R.id.testing);


        parent = findViewById(R.id.parent);



        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        topAppbar =findViewById(R.id.topAppbar);
        topAppbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleHotel.this, User_homepage.class));
            }
        });






        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter(this, simple_spinner_item, number);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    number_of_rooms = String.valueOf(adapterView.getSelectedItem());
                    testing.setText(adapterView.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        int day1;


        date_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day1 = calendar.get(Calendar.DAY_OF_MONTH);
                int month1 = calendar.get(Calendar.MONTH);
                int year1 = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(SingleHotel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        display_date_1.setVisibility(View.VISIBLE);
                        from_date = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
                        display_date_1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year1, month1, day1);
                picker.show();
            }
        });





        date_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day2 = calendar.get(Calendar.DAY_OF_MONTH);
                int month2 = calendar.get(Calendar.MONTH);
                int year2 = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(SingleHotel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        display_date_2.setVisibility(View.VISIBLE);
                        to_date = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
                        display_date_2.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, year2, month2, day2);
                picker.show();
            }
        });










                Picasso.get().load(getIntent().getStringExtra("singlehotel_image"))
                .placeholder(R.drawable.ic_launcher_background)
                .into(singleHimage);


        singleHname.setText(getIntent().getStringExtra("singlehotel_name"));
        singleHplace.setText(getIntent().getStringExtra("singlehotel_place"));
        singleHdesc.setText(getIntent().getStringExtra("singlehotel_desc"));
        singleHphone.setText(getIntent().getStringExtra("singlehotel_phone"));
        singleHpincode.setText(getIntent().getStringExtra("singlehotel_pincode"));
        singleHcity.setText(getIntent().getStringExtra("singlehotel_city"));
        singleHstreet.setText(getIntent().getStringExtra("singlehotel_street"));
        singleHprice.setText(getIntent().getStringExtra("singlehotel_price"));


        topAppbar.setTitle(getIntent().getStringExtra("singlehotel_name"));


        String hotel_name = getIntent().getStringExtra("singlehotel_name");

        String hotel_place = getIntent().getStringExtra("singlehotel_place");

        String hotel_price = getIntent().getStringExtra("singlehotel_price");

        String client_email = getIntent().getStringExtra("singleowner_email");

        String hotel_image = getIntent().getStringExtra("singlehotel_image");











        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    //user is signed in
                    String UID = firebaseUser.getUid();
                    firebaseDatabase.getReference().child("Users").child(UID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String full_name, user_email;
                            full_name = snapshot.child("full_name").getValue().toString();
                            user_email = snapshot.child("user_email").getValue().toString();
                            String newRef = firebaseDatabase.getReference().child("Transactions").push().getKey();




                            Long price = Long.parseLong(hotel_price);
                            Long number_rooms_long = Long.parseLong(number_of_rooms);
                            Long total_cost_1 = number_rooms_long * price;

                            String total_cost = String.valueOf(total_cost_1);

                            Transactions transactions = new Transactions(full_name, user_email, client_email, hotel_name, hotel_place, from_date, to_date, total_cost, number_of_rooms, "Booked", hotel_image, newRef);

                            firebaseDatabase.getReference().child("Transactions").child(newRef).setValue(transactions);
                            Snackbar.make(parent, "Your Hotel has been booked", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(SingleHotel.this, User_homepage.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    Snackbar.make(parent, "Please Sign IN to Book", Snackbar.LENGTH_LONG)
                            .setAction("Sign IN", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(SingleHotel.this, Login.class));
                                }
                            })
                            .show();
                }
            }
        });




    }

}