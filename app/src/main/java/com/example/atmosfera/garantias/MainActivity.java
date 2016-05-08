package com.example.atmosfera.garantias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onclick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.linearGarantias:
                intent = new Intent(this, MisGarantiasActivity.class);
                break;
            case R.id.linearAddGarantia:
                //code
                break;
            case R.id.linearSettings:
                //code
                break;
            case R.id.linearInfo:
                //code
                break;
        }
        startActivity(intent);
    }
}


