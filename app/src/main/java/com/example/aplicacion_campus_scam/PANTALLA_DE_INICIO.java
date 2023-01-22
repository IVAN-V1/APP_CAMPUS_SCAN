package com.example.aplicacion_campus_scam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.aplicacion_campus_scam.Actividades.Menu_principal;

public class PANTALLA_DE_INICIO extends AppCompatActivity {

    // tiempo de la pantalla
    private static int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //cambiar de pantalla

                Intent i = new Intent(PANTALLA_DE_INICIO.this, Menu_principal.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIMER);

    }

    }
