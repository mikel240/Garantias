package com.example.atmosfera.garantias.controllers.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.adapters.ProveedoresAdapter;
import com.example.atmosfera.garantias.databases.DataBaseHelper;
import com.example.atmosfera.garantias.models.Marca;

import java.util.ArrayList;

public class ProveedoresFragment extends Fragment {

    private ArrayList<Marca> listaMarcas;
    private ListView lvProveedores;
    private View view;
    private ProveedoresAdapter paProveedores;

    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_proveedores_fragment, container, false);

        listaMarcas = DataBaseHelper.getInstance(getActivity()).getMarcas();

        lvProveedores = (ListView) view.findViewById(R.id.lvProveedores);

        paProveedores = new ProveedoresAdapter(getContext(), listaMarcas);
        lvProveedores.setAdapter(paProveedores);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data from Argument
        position = getArguments().getInt("position");
    }

    public static Fragment getInstance(int position) {
        ProveedoresFragment f = new ProveedoresFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }
}
