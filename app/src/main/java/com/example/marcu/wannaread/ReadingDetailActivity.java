package com.example.marcu.wannaread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReadingDetailActivity extends AppCompatActivity {

    public static final String EXTRA_READING = "reading";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_detail);
    }
}
