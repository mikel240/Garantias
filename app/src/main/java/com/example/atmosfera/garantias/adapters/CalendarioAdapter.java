package com.example.atmosfera.garantias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.models.Garantia;

import java.util.ArrayList;

public class CalendarioAdapter extends ArrayAdapter<Object> {

    private Context context;
    private ArrayList<Object> items;
    private LayoutInflater layoutInflater;

    public CalendarioAdapter(Context context, ArrayList<Object> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        Object o = items.get(position);
        if (o != null) {
            if (o instanceof String) {
                String fecha = (String) o;
                v = layoutInflater.inflate(R.layout.item_fecha, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                TextView tvFecha = (TextView) v.findViewById(R.id.tvFecha);
                tvFecha.setText(fecha);

            } else {
                Garantia g = (Garantia) o;
                v = layoutInflater.inflate(R.layout.item_garantia, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                TextView tvTipo = (TextView) v.findViewById(R.id.tvTipo);
                tvTipo.setText(g.getTipoProductoG());

                TextView tvMarca = (TextView) v.findViewById(R.id.tvMarca);
                tvMarca.setText(g.getObjMarcaG().getNombreM());

                TextView tvModelo = (TextView) v.findViewById(R.id.tvModelo);
                tvModelo.setText(g.getModeloG());

                TextView tvFecha = (TextView) v.findViewById(R.id.tvFecha);
                tvFecha.setText(g.getFechaCompraG());
            }
        }
        return v;
    }

}