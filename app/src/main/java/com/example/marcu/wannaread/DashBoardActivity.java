package com.example.marcu.wannaread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Infla o fragment

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new DashBoardFragment())
                .commit();
    }
}
