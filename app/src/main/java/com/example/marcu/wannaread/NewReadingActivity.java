package com.example.marcu.wannaread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewReadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reading);

        getSupportActionBar().hide();

        // Infla o fragment

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new NewReadingFragment())
                .commit();

    }
}
