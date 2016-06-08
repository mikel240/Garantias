package com.example.atmosfera.garantias.controllers.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmosfera.garantias.R;

public class CameraActivity extends AppCompatActivity {

    ImageView imageGarantia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_camera_activity));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Fuentes
        TextView txt_camera = (TextView) findViewById(R.id.txt_camera);
        txt_camera.setTypeface(Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/Ago.ttf"));

        //Camera
        imageGarantia = (ImageView) findViewById(R.id.imageGarantia);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera_toolbar, menu);
        return true;
    }

    //back arrow in a toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_ok:
                //Que se hace al pulsar el tick ("Finalizar")
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Abre la cámara para sacar la imagen
    public void openCamera(View v) {
        Intent i = new Intent();
        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 1);
    }

    //Muestra la imagen en el imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap mybit = (Bitmap) extras.get("data");
            imageGarantia.setImageBitmap(mybit);
        }
    }
}
