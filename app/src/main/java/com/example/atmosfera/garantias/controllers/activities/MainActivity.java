package com.example.atmosfera.garantias.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.atmosfera.garantias.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Garantiator");
        setSupportActionBar(toolbar);

        /*
        // Añadir varias marcas a la DB (eliminar más adelante)
        DataBaseHelper.getInstance(this).clearAllMarcas();
        DataBaseHelper.getInstance(this).addMarca("LG", "support@lg.com");
        DataBaseHelper.getInstance(this).addMarca("Philips", "support@philips.com");
        DataBaseHelper.getInstance(this).addMarca("Acer", "support@acer.com");
        DataBaseHelper.getInstance(this).addMarca("Apple", "support@apple.com");
        DataBaseHelper.getInstance(this).addMarca("HP", "support@hp.com");
        DataBaseHelper.getInstance(this).addMarca("Logitech", "support@logitech.com");
        DataBaseHelper.getInstance(this).addMarca("Huawei", "support@huawei.com");
        DataBaseHelper.getInstance(this).addMarca("Hyundai", "support@hyundai.com");
        DataBaseHelper.getInstance(this).addMarca("Sony", "support@sony.com");
        DataBaseHelper.getInstance(this).addMarca("Asus", "support@asus.com");
        DataBaseHelper.getInstance(this).addMarca("Samsung", "support@samsung.com");
        DataBaseHelper.getInstance(this).addMarca("Xiaomi", "support@xiaomi.com");
        DataBaseHelper.getInstance(this).addMarca("bq", "support@bq.com");
        DataBaseHelper.getInstance(this).addMarca("TP-Link", "support@tplink.com");
*/
    }

    public void onclick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.linearGarantias:
                intent = new Intent(this, MisGarantiasActivity.class);
                break;
            case R.id.linearAddGarantia:
                intent = new Intent(this, AddGarantiaActivity.class);
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


