package com.example.v3_.FRAGMENTOS;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.v3_.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class actividad_para_fragmentos extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    HOME escanear = new HOME();
    pdf crear_pdf = new pdf();
    lista_registros lista = new lista_registros();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_para_fragmentos);


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
