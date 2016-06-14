package com.example.atmosfera.garantias.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.models.Marca;

import java.util.ArrayList;

public class ProveedoresAdapter extends ArrayAdapter<Marca> {
    private Context context;
    private ArrayList<Marca> items;
    private LayoutInflater layoutInflater;

    public ProveedoresAdapter(Context context, ArrayList<Marca> items) {
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
            if (o instanceof Marca) {
                Marca m = (Marca) o;
                v = layoutInflater.inflate(R.layout.item_proveedor, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                TextView tvNombre = (TextView) v.findViewById(R.id.tvNombre);
                tvNombre.setText(m.getNombreM());

                TextView tvCorreo = (TextView) v.findViewById(R.id.tvCorreo);
                tvCorreo.setText(m.getCorreoM());
            }
        }
        return v;
    }

}