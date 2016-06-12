package com.example.atmosfera.garantias.controllers.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.atmosfera.garantias.R;

public class InfoActivity extends AppCompatActivity {

    TextView nom1;
    TextView nom2;
    TextView nom3;
    TextView designed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_info));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Fuentes
        nom1 = (TextView) findViewById(R.id.nom1);
        nom2 = (TextView) findViewById(R.id.nom2);
        nom3 = (TextView) findViewById(R.id.nom3);
        designed = (TextView) findViewById(R.id.designedBy);

        nom1.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Bulldozer.ttf"));
        nom2.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Bulldozer.ttf"));
        nom3.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Bulldozer.ttf"));
        designed.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Ago.ttf"));

    }

    //back arrow in a toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

