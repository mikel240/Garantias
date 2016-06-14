package com.example.atmosfera.garantias.controllers.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.controllers.fragments.ListadoFragment;
import com.example.atmosfera.garantias.databases.DataBaseHelper;
import com.example.atmosfera.garantias.models.Marca;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    ImageView imageGarantia;
    Bitmap bm;
    private final String FOLDER_NAME = "garantiator";
    private String productTipo;
    private String productMarca;
    private String productModelo;
    private String localCompra;
    private String fechaCompra;
    private int duracionGarantia;
    private long marca;
    private long factura;
    private String photoPath;

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
                crearGarantia();

                Intent i = new Intent(CameraActivity.this, MisGarantiasActivity.class);
                startActivity(i);

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
            imageGarantia.buildDrawingCache();
            bm = imageGarantia.getDrawingCache();
        }
    }

    private void crearGarantia() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productTipo = extras.getString("productTipo");
            productMarca = extras.getString("productMarca");
            productModelo = extras.getString("productModelo");
            localCompra = extras.getString("localCompra");
            fechaCompra = extras.getString("fechaCompra");
            duracionGarantia = extras.getInt("duracionGarantia", 1);
        }


        ArrayList<Marca> listaMarcas = DataBaseHelper.getInstance(this).getMarcas();

        marca = -1;

        for (Marca m : listaMarcas) {
            if (m.getNombreM().equalsIgnoreCase(productMarca)) {
                marca = m.getIdM();
                return;
            }
        }

        if (marca == -1) {
            String mail = "support@".concat(productMarca.toLowerCase()).concat(".com");
            marca = DataBaseHelper.getInstance(this).addMarca(productMarca, mail);
        }

        photoPath = "";
        try {
            saveImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        factura = DataBaseHelper.getInstance(this).addFactura(photoPath, "JPEG");

        DataBaseHelper.getInstance(this).addGarantia(productTipo, marca, productModelo,
                fechaCompra, localCompra, duracionGarantia, factura);

    }


    private void saveImage() throws Exception {
        OutputStream fOut = null;

        File root = new File(Environment.getExternalStorageDirectory() + File.separator + FOLDER_NAME + File.separator);
        root.mkdirs();
        String temp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File sdImageMainDirectory = new File(root, "garantiator_" + temp + ".jpg");
        photoPath = Uri.fromFile(sdImageMainDirectory).toString();
        fOut = new FileOutputStream(sdImageMainDirectory);

        bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        fOut.flush();
        fOut.close();
    }

}
