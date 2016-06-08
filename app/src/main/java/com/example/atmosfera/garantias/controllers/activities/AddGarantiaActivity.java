package com.example.atmosfera.garantias.controllers.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmosfera.garantias.R;

import java.util.Calendar;

public class AddGarantiaActivity extends AppCompatActivity {

    private TextView displayFechaCompra;
    private ImageView pickCalendar;
    Calendar calendar;
    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATEPICKER_COMPRA_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_garantia);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_form_add));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //spinner
        Spinner spnDuracionGarantia = (Spinner) findViewById(R.id.spn_dur_garantia);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_spn_dur_garantia));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDuracionGarantia.setAdapter(adapter);


        //DatePicker
        displayFechaCompra = (TextView) findViewById(R.id.fecha_compra);
        pickCalendar = (ImageView) findViewById(R.id.im_calendar);

        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Fuentes
        TextView txt_siguiente = (TextView) findViewById(R.id.txt_siguiente);
        txt_siguiente.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Ago.ttf"));

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

    public void updateDisplay() {
        this.displayFechaCompra.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mDay).append("/")
                .append(mMonth + 1).append("/")
                .append(mYear).append(" "));
    }

    public DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATEPICKER_COMPRA_ID)
            return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);

        return null;
    }

    public void openCalendar(View v) {
        showDialog(DATEPICKER_COMPRA_ID);
    }

    public void openCameraActivity(View v) {
        String message = checkFields();
        if (message.length() != 0) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }
    }

    public String checkFields() {
        String message = "";
        EditText product_tipo = (EditText) findViewById(R.id.product_tipo);
        EditText product_marca = (EditText) findViewById(R.id.product_marca);
        EditText product_modelo = (EditText) findViewById(R.id.product_modelo);
        EditText local_compra = (EditText) findViewById(R.id.local_compra);
        EditText fecha_compra = (EditText) findViewById(R.id.fecha_compra);

        if (product_tipo.getText().toString().equals(""))
            message = getResources().getString(R.string.campo_tipoProducto_vacio);

        if (product_marca.getText().toString().equals(""))
            message += "\n" + getResources().getString(R.string.campo_marca_vacio);

        if (product_modelo.getText().toString().equals(""))
            message += "\n" + getResources().getString(R.string.campo_modelo_vacio);

        if (local_compra.getText().toString().equals(""))
            message += "\n" + getResources().getString(R.string.campo_lugar_vacio);

        if (fecha_compra.getText().toString().equals(""))
            message += "\n" + getResources().getString(R.string.campo_fecha_vacio);

        return message;
    }

}
