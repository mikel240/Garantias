package com.example.atmosfera.garantias.controllers.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.atmosfera.garantias.R;

import java.io.File;

public class test extends Activity {

    private ImageView mSetImage;
    private Button mOptionButton;
    private Uri imageCaptureUri;
    static final int PICK_FROM_CAMERA = 1;
    static final int PICK_FROM_FILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mSetImage = (ImageView) findViewById(R.id.imageGarantia);

    }

    public void openCamera(View v){
        Intent i = new Intent();
        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap mybit = (Bitmap) extras.get("data");
            mSetImage.setImageBitmap(mybit);
        }
    }
}