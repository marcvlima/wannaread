package com.example.marcu.wannaread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marcu.wannaread.database.Reading;

public class ReadingDetailActivity extends AppCompatActivity {

    public static final String EXTRA_READING = "reading";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);

        // Infla o fragment
        Intent intent = getIntent();
        Reading reading = (Reading)intent.getParcelableExtra(this.EXTRA_READING);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ReadingDetailFragment.newInstance(reading))
                .commit();
    }
}
