package com.example.notefy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // Variables
    BottomNavigationView bottomNavigationView;
    CreateNote cnote = new CreateNote();
    ListNotes lnotes = new ListNotes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.newNote);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable;
        if (BuildConfig.FLAVOR.equals("app1")) {
            colorDrawable = new ColorDrawable(Color.parseColor("#F44336"));
        } else {
            colorDrawable = new ColorDrawable(Color.parseColor("#4CAF50"));
        }
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.newNote:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, cnote).commit();
                return true;

            case R.id.listNotes:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, lnotes).commit();
                return true;
        }
        return false;
    }

}