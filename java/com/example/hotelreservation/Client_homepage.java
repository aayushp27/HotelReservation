package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Client_homepage extends AppCompatActivity {
    ArrayList<Transactions> trans_recyclelist;
    RecyclerView ClientHistoryRecView;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_homepage);

        toolbar = findViewById(R.id.client_toolbar);
        bottomNavigationView = findViewById(R.id.clientBottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.history);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.hotel:
                        startActivity(new Intent(Client_homepage.this, client_hotel.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.history:
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(Client_homepage.this, client_profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }


                return false;
            }

        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.sign_in:
                        startActivity(new Intent(Client_homepage.this, Login.class));
                        return true;

                    case R.id.sign_out:
                        firebaseAuth.signOut();
                        startActivity(new Intent(Client_homepage.this, Login.class));
                        Toast.makeText(Client_homepage.this, "User Signed Out", Toast.LENGTH_LONG).show();
                        return true;


                }
                return false;
            }
        });



        ClientHistoryRecView = findViewById(R.id.ClientHistoryRecView);
        trans_recyclelist = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        ClientHistoryAdaptor historyAdaptor = new ClientHistoryAdaptor(trans_recyclelist, Client_homepage.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Client_homepage.this);
        ClientHistoryRecView.setLayoutManager(linearLayoutManager);
        ClientHistoryRecView.addItemDecoration(new DividerItemDecoration(ClientHistoryRecView.getContext(), DividerItemDecoration.VERTICAL));
        ClientHistoryRecView.setNestedScrollingEnabled(false);
        ClientHistoryRecView.setAdapter(historyAdaptor);


        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String email = firebaseUser.getEmail();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Transactions");
        Query query = databaseReference.orderByChild("client_email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trans_recyclelist.clear();
                if(snapshot.exists())
                {
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        Transactions transactions = snapshot1.getValue(Transactions.class);
                        trans_recyclelist.add(transactions);
                    }
                    historyAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}