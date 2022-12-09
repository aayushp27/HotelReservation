package com.example.hotelreservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class User_homepage extends AppCompatActivity {

    ArrayList<Hotels> recyclelist;
    RecyclerView UserHotelRecView;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigationView;
    Toolbar userHOMEtoolbar;
    FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();


    private UserHotelAdaptor hotelAdaptor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        UserHotelRecView = findViewById(R.id.UserHotelRecView);
        recyclelist = new ArrayList<>();
        userHOMEtoolbar = findViewById(R.id.userHOMEtoolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        userHOMEtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();

                switch (item.getItemId()) {
                    case R.id.sign_in:
                        startActivity(new Intent(User_homepage.this, Login.class));
                        return true;

                    case R.id.sign_out:
                        firebaseAuth.signOut();
                        startActivity(new Intent(User_homepage.this, User_homepage.class));
                        Toast.makeText(User_homepage.this, "User Signed Out", Toast.LENGTH_LONG).show();
                        return true;

            }
                return false;
            }
        });

        FirebaseUser user = firebaseAuth.getCurrentUser();

        firebaseDatabase.getReference().child("Hotels").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    Hotels hotels = dataSnapshot.getValue(Hotels.class);
                    recyclelist.add(hotels);

                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(User_homepage.this);
                UserHotelRecView.setLayoutManager(linearLayoutManager);
                UserHotelRecView.addItemDecoration(new DividerItemDecoration(UserHotelRecView.getContext(), DividerItemDecoration.VERTICAL));
                UserHotelRecView.setNestedScrollingEnabled(false);
                UserHotelAdaptor hotelAdaptor = new UserHotelAdaptor(recyclelist, getApplicationContext());
                UserHotelRecView.setAdapter(hotelAdaptor);

                hotelAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        bottomNavigationView = findViewById(R.id.userBottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                switch (item.getItemId())
                {
                    case R.id.history:
                        if (user1 != null) {
                             startActivity(new Intent(getApplicationContext(), user_history.class));
                             overridePendingTransition(0,0);
                             return true;
                        }

                    case R.id.home:
                        return true;

                    case R.id.profile:
                        if (user1 != null) {
                            startActivity(new Intent(getApplicationContext(), user_profile.class));
                            overridePendingTransition(0, 0);
                            return true;
                        }
                }


                return false;
            }

            }
        );

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(user1 != null ){
            menu.removeItem(R.id.sign_in);
        }else{
            menu.removeItem(R.id.sign_out);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenubar, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hotelAdaptor.getFilter().filter(newText);
                return false;
            }
        });


        return true;
    }


    @Override
    protected void onStart() {



        FirebaseUser user = firebaseAuth.getCurrentUser();
        super.onStart();
        if (user != null)
        {

        }
        else
        {

        }
    }
}