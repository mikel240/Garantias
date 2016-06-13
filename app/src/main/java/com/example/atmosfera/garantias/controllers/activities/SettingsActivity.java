package com.example.atmosfera.garantias.controllers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

import com.example.atmosfera.garantias.R;


public class SettingsActivity extends AppCompatActivity {

    Switch notifies;
    Switch silenciar;
    CheckBox actualizar;
    Spinner language;
    Locale myLocale;

    // define the SharedPreferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_settings));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //spinner
        final Spinner spnLanguage = (Spinner) findViewById(R.id.spn_lang);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spn_language));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLanguage.setAdapter(adapter);

        spnLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spnLanguage.getSelectedItemPosition();
                switch (language.getSelectedItem().toString()) {
                    case "Inglés":
                        changeLanguage("en");
                        break;
                    case "Spanish":
                        changeLanguage("es");
                        break;
                }

            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        //
        savedValues = getPreferences(MODE_PRIVATE);
        notifies = (Switch) findViewById(R.id.switch_notify);
        silenciar = (Switch) findViewById(R.id.switch_silenciar);
        actualizar = (CheckBox) findViewById(R.id.check_act);
        language = (Spinner) findViewById(R.id.spn_lang);

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

    @Override
    public void onPause() {
        // save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putBoolean("notifies", notifies.isChecked());
        editor.putBoolean("silenciar", silenciar.isChecked());
        editor.putBoolean("actualizar", actualizar.isChecked());
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {
        //set values
        notifies.setChecked(savedValues.getBoolean("notifies", true));
        silenciar.setChecked(savedValues.getBoolean("silenciar", false));
        actualizar.setChecked(savedValues.getBoolean("actualizar", true));
        super.onResume();
    }

    public void changeLanguage(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

}
