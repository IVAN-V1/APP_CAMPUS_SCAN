package com.example.aplicacion_campus_scam.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplicacion_campus_scam.FRAGMENTOS.funcion_de_fragmentos;
import com.example.aplicacion_campus_scam.R;

public class Menu_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


    }


    //CAMBIAR PANTALLA
    public void cambio_registr_a(View view){

        Intent i = new Intent(Menu_principal.this, menu_QR.class);
        startActivity(i);



    }

    //CAMBUAR PANTALLA
    public void cambio_registro_maestro(View view){

        Intent i = new Intent(Menu_principal.this, funcion_de_fragmentos.class);
        startActivity(i);


    }


}