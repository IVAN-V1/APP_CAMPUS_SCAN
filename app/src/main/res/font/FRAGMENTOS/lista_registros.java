package com.example.v3_.FRAGMENTOS;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.v3_.R;
import com.example.v3_.db.BASE_DE_DATOS_DE_REGISTROS;

import java.util.ArrayList;
import java.util.List;


public class lista_registros extends Fragment {

    private List<String> mlista = new ArrayList<>();
    private ArrayAdapter adapter;

    ListView listView;


    public lista_registros() {
        // Required empty public constructor



    }


    public static lista_registros newInstance(String param1, String param2) {
        lista_registros fragment = new lista_registros();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_lista, container, false);

        listView= view.findViewById(R.id.lista);


            mlista.clear();

        try {

            BASE_DE_DATOS_DE_REGISTROS admin = new BASE_DE_DATOS_DE_REGISTROS(getContext(), "REGISTROS", null, 2);
            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

            //recuperamos los datos mediante el codigo de producto

            // rawQuery es un select en esta base de datos
            Cursor fila = baseDeDatos.rawQuery("select * from registros ", null);

            //el metodo moveToFirst revisa si la consulta tiene valores
            if (fila.moveToFirst()) {

                do {

                    mlista.add("REGISTRO NUMERO: "+fila.getString(0));

                    mlista.add(fila.getString(1));




                }while (fila.moveToNext());

                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mlista);
                listView.setAdapter(adapter);



            }



        }catch (Exception e){



        }


        return view;



    }



}