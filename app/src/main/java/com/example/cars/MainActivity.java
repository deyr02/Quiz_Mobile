package com.example.cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonSerializer;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout drawer;
    private  API api;
    private TextView userName, userEmail;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().hasExtra("userObject")){
            user = (User) getIntent().getSerializableExtra("userObject");
        }
        //Creating API object
        api = new API(MainActivity.this);

        //Drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ///Nav header
        View header = navigationView.getHeaderView(0);
        userName = (TextView) header.findViewById(R.id.userName);
        userEmail= (TextView)header.findViewById(R.id.user_email);
        userName.setText(user.getFirstName() + " "+ user.getLastName());
        userEmail.setText(user.getEmail());



        //Nav item seclection
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) MainActivity.this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        //Default selected fragment
        Bundle bundle = new Bundle();
        bundle.putInt("userId", user.getId());
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                homeFragment).commit();

        navigationView.setCheckedItem(R.id.nav_home);



    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Bundle bundle = new Bundle();
                bundle.putInt("userId", user.getId());
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        homeFragment).commit();
                break;
            case R.id.nav_start_quiz:

                api.createAttempt(user.getId(), new APIResponseListener<Attempt>() {
                    @Override
                    public void onError(VolleyError error) {
                        Log.e("CreateAttempt", error.getMessage());
                    }

                    @Override
                    public void onResponse(Attempt response) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("attemptId", response.getID());
                        bundle.putInt("isSubmitted", response.getIsSubmitted());
                        bundle.putInt("totalQuestions", response.getTotalQuestions());
                        bundle.putInt("totalCorrectAnswer", response.getTotalCorrectAnswer());
                        bundle.putString("startedAt", response.getStartedAt());
                        bundle.putString("finishedAt", response.getFinishedAT());
                        bundle.putInt("userId", response.getUserId());
                        StartQuizFragment startQuizFragment = new StartQuizFragment();
                        startQuizFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                startQuizFragment).commit();
                    }
                });

                break;
            case R.id.nav_previous_attempt:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("userId", user.getId());
                PreviousAttemptFragment previousAttemptFragment = new PreviousAttemptFragment();
                previousAttemptFragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        previousAttemptFragment).commit();
                break;

            case R.id.nav_all_questions:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllQuestionFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}