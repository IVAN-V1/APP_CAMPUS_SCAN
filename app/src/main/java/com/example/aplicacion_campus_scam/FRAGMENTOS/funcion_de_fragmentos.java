package com.example.aplicacion_campus_scam.FRAGMENTOS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.aplicacion_campus_scam.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class funcion_de_fragmentos extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    Escaner escanear = new Escaner();
    pdf crear_pdf = new pdf();
    lista lista = new lista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcion_de_fragmentos);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, escanear).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.icono_escanear:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, escanear).commit();
                        return true;
                    case R.id.icono_lista:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, lista).commit();
                        return true;
                    case R.id.icono_crear_pdf:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, crear_pdf).commit();
                        return true;

                }
                return false;
            }

        });


    }

    }
