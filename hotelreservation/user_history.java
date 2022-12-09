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

public class user_history extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    ArrayList<Transactions> trans_recyclelist;
    RecyclerView UserHistoryRecView;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);


        toolbar = findViewById(R.id.userHtoolbar);
        bottomNavigationView = findViewById(R.id.userBottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.history);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  switch (item.getItemId())
                  {
                      case R.id.history:
                          return true;

                          case R.id.home:
                              startActivity(new Intent(getApplicationContext(), User_homepage.class));
                              overridePendingTransition(0,0);
                              return true;

                              case R.id.profile:
                                  startActivity(new Intent(getApplicationContext(), user_profile.class));
                                  overridePendingTransition(0,0);
                                  return true;
                  }


                  return false;
              }
        });

        UserHistoryRecView = findViewById(R.id.UserHistoryRecView);
        trans_recyclelist = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        UserHistoryAdaptor historyAdaptor = new UserHistoryAdaptor(trans_recyclelist, user_history.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(user_history.this);
        UserHistoryRecView.setLayoutManager(linearLayoutManager);
        UserHistoryRecView.addItemDecoration(new DividerItemDecoration(UserHistoryRecView.getContext(), DividerItemDecoration.VERTICAL));
        UserHistoryRecView.setNestedScrollingEnabled(false);
        UserHistoryRecView.setAdapter(historyAdaptor);


        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String email = firebaseUser.getEmail();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Transactions");
        Query query = databaseReference.orderByChild("user_email").equalTo(email);
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