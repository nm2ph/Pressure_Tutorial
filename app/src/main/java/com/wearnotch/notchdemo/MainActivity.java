package com.wearnotch.notchdemo;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wearnotch.service.NotchAndroidService;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    // Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit();
        }

        Intent controlServiceIntent = new Intent(this, NotchAndroidService.class);
        startService(controlServiceIntent);

        // to develop app UI without notches you can use a 'mock' version of the SDK
        // it returns success for all SDK calls, to use it uncomment this line
        // controlServiceIntent.putExtra("MOCK", true);

        bindService(controlServiceIntent, this, BIND_AUTO_CREATE);

        // Write a message to the database
        myRef.setValue("Hello, World!");



    }
}
