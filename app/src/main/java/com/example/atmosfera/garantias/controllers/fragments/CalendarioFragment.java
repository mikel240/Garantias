package com.example.atmosfera.garantias.controllers.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.adapters.CalendarioAdapter;
import com.example.atmosfera.garantias.adapters.ListadoAdapter;
import com.example.atmosfera.garantias.databases.DataBaseHelper;
import com.example.atmosfera.garantias.models.Garantia;

import java.util.ArrayList;
import java.util.Objects;

public class CalendarioFragment extends Fragment {

    private ArrayList<Garantia> listaGarantias;
    private ArrayList<Object> listaObjetos;
    private ListView lvCalendario;
    private View view;
    private CalendarioAdapter caCalendario;

    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_calendario_fragment, container, false);

        listaGarantias = DataBaseHelper.getInstance(getActivity()).getGarantias();

        listaObjetos = new ArrayList<>();
        listaGarantias = DataBaseHelper.getInstance(getActivity()).getGarantias();

        String fechaAux = "";
        Garantia gAux = null;

        for (Garantia g : listaGarantias) {
            gAux = g;

            int anyo = Integer.valueOf(g.getFechaCompraG().substring(6, 10)) + g.getDuracionG();

            fechaAux = g.getFechaCompraG().substring(0, 6) + anyo;
            gAux.setFechaCompraG(fechaAux);

            listaObjetos.add(fechaAux);
            listaObjetos.add(gAux);
        }


        lvCalendario = (ListView) view.findViewById(R.id.lvCalendario);

        caCalendario = new CalendarioAdapter(getContext(), listaObjetos);
        lvCalendario.setAdapter(caCalendario);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data from Argument
        position = getArguments().getInt("position");
    }

    public static Fragment getInstance(int position) {
        CalendarioFragment f = new CalendarioFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }
}
