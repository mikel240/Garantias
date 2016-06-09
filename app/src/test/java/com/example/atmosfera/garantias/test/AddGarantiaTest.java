package com.example.atmosfera.garantias.test;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.atmosfera.garantias.BuildConfig;
import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.controllers.activities.AddGarantiaActivity;
import com.example.atmosfera.garantias.controllers.activities.CameraActivity;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AddGarantiaTest {

    private AddGarantiaActivity activity;
    private ImageView nextActivity;
    private EditText tipo;
    private EditText marca;
    private EditText modelo;
    private EditText lugar;
    private EditText fechaCompra;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(AddGarantiaActivity.class).create().get();

        tipo = (EditText) activity.findViewById(R.id.product_tipo);
        marca = (EditText) activity.findViewById(R.id.product_marca);
        modelo = (EditText) activity.findViewById(R.id.product_modelo);
        lugar = (EditText) activity.findViewById(R.id.local_compra);
        fechaCompra = (EditText) activity.findViewById(R.id.fecha_compra);

        nextActivity = (ImageView) activity.findViewById(R.id.im_siguiente);
    }

    @Test
    //Si todos los campos del formulario están completados pasaremos a la siguiente actividad.
    public void pressSiguienteWithOutEmptyFieldsPassNextActivity() throws Exception {
        tipo.setText("Televisión");
        marca.setText("LG");
        modelo.setText("ZXY-32");
        lugar.setText("MediaMarkt");
        fechaCompra.setText("08/06/2016");
        passNextActivity();

    }

    //Si falta algún campo del formulario no pasaremos a la siguiente actividad.
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void pressSiguienteWithEmptyTipoNotPassNextActivity() throws Exception {
        tipo.setText("");
        marca.setText("LG");
        modelo.setText("ZXY-32");
        lugar.setText("MediaMarkt");
        fechaCompra.setText("08/06/2016");
        notPassNextActivity();
    }

    @Test
    public void pressSiguienteWithEmptyMarcaNotPassNextActivity() throws Exception {
        tipo.setText("Televisión");
        marca.setText("");
        modelo.setText("ZXY-32");
        lugar.setText("MediaMarkt");
        fechaCompra.setText("08/06/2016");
        notPassNextActivity();
    }

    @Test
    public void pressSiguienteWithEmptyModleoNotPassNextActivity() throws Exception {
        tipo.setText("Televisión");
        marca.setText("LG");
        modelo.setText("");
        lugar.setText("MediaMarkt");
        fechaCompra.setText("08/06/2016");
        notPassNextActivity();
    }

    @Test
    public void pressSiguienteWithEmptyLugarNotPassNextActivity() throws Exception {
        tipo.setText("Televisión");
        marca.setText("LG");
        modelo.setText("ZXY-32");
        lugar.setText("");
        fechaCompra.setText("08/06/2016");
        notPassNextActivity();
    }

    @Test
    public void pressSiguienteWithEmptyFechaCompraNotPassNextActivity() throws Exception {
        tipo.setText("Televisión");
        marca.setText("LG");
        modelo.setText("ZXY-32");
        lugar.setText("MediaMarkt");
        fechaCompra.setText("");
        notPassNextActivity();
    }


    public void passNextActivity() {
        nextActivity.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Shadows.shadowOf(startedIntent);
        Assert.assertThat(shadowIntent.getComponent().getClassName(), IsEqual.equalTo(CameraActivity.class.getName()));
    }


    public void notPassNextActivity() {
        nextActivity.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent startedIntent=null;
        try {
            startedIntent = shadowActivity.getNextStartedActivity();
        } catch (Exception e) {
            //No pasa a la siguiente actividad
        }
        Assert.assertTrue(startedIntent==null);
    }
}
