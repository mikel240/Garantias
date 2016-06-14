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
import com.example.atmosfera.garantias.adapters.ListadoAdapter;
import com.example.atmosfera.garantias.controllers.activities.MainActivity;
import com.example.atmosfera.garantias.databases.DataBaseHelper;
import com.example.atmosfera.garantias.models.Garantia;

import java.util.ArrayList;

public class ListadoFragment extends Fragment {

    private ArrayList<Garantia> listaGarantias;
    private ListView lvListado;
    private View view;
    private ListadoAdapter laListado;


    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_listado_fragment, container, false);

        listaGarantias = DataBaseHelper.getInstance(getActivity()).getGarantias();

        lvListado = (ListView) view.findViewById(R.id.lvListado);

        laListado = new ListadoAdapter(getContext(), listaGarantias);
        lvListado.setAdapter(laListado);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data from Argument
        position = getArguments().getInt("position");
    }

    public static Fragment getInstance(int position) {
        ListadoFragment f = new ListadoFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }
}
