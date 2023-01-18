package com.example.aplicacion_campus_scam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacion_campus_scam.db.BasedeDatos_alumnos;

public class menu_QR extends AppCompatActivity {

    EditText nom, matri;

    private SQLiteDatabase db;
    private BasedeDatos_alumnos b;


    AutoCompleteTextView completador_;

    AutoCompleteTextView completador_2;



    AutoCompleteTextView completador_compu;

    String [] computadoras ={"NO OCUPE", "1", "2","3","4","5","6","7","8","9","10","11","12","13"


    };

    AutoCompleteTextView repote_fallas;

    String [] fallas ={"NINGUNA", "NO TIENE MAUS", "NO TIENE TECLADO","NO FUNCIONA EL TECLADO"


    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_qr);


        nom = findViewById(R.id.R_nombre);

        completador_2=findViewById(R.id.completador_compus);
        repote_fallas=findViewById(R.id.completador_fallas);

        //arreglos
        ArrayAdapter<String> adapter2= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , computadoras);
        completador_2.setAdapter(adapter2);

        //segundo arreglo
        ArrayAdapter<String> adapter4= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , fallas);
        repote_fallas.setAdapter(adapter4);




        // creamos la base de datos
        // b = new BasedeDatos_alumnos(this, "BASE_DE_DATOS_ALUMNOS", null, 2);
        // la abrimos en modo escritura
        //db = b.getWritableDatabase();




    }



    //METODO PARA EL REGISTRO
    public void Registrar(View view) {

        String nombre_ = nom.getText().toString().toUpperCase();
        String num_compu = completador_2.getText().toString().toUpperCase();
        String fallas_repote = repote_fallas.getText().toString().toUpperCase();

// VALIDAR SI LOS CAMPOS ESTAN VACIOS SI NO ENVIAR MENSAJE
        if (nombre_.equals("") || num_compu.equals("") || fallas_repote.equals("")) {

            //mensaje en pantalla
            Toast.makeText(this, "Debes llenar todos los campos",
                    Toast.LENGTH_SHORT).show();
        }


        //
        else {

            //cambiar pantalla
            Intent pantalla = new Intent(menu_QR.this,generador_QR.class);
            startActivity(pantalla);

            //pasar datos a otra pantalla
            Bundle pasarDatos = new Bundle();
            pasarDatos.putString("pNombre",nombre_);
            pasarDatos.putString("pcompu",num_compu);
            pasarDatos.putString("pfallas",fallas_repote);

            pantalla.putExtras(pasarDatos);
            startActivity(pantalla);

            //USAMOS Try Y Cach PARA VER SI SE CREO BIEN LA BASE DE DATOS SI NO MANDA MENSAJE
            /*
             * Try significa intenta
             *
             * catch signifca si no has esto
             *
             * */

               /*
                // BASE DE DATOS

            try {

                // creamos un ContentValue para guardar la informacion en la base de datos
                ContentValues nuevoRegistro = new ContentValues();
                // insertamos los datos en el ContentValues
                nuevoRegistro.put("nombre", nombre_);
                nuevoRegistro.put("matricula", matricula_);
                nuevoRegistro.put("carrera", carrera_);
                nuevoRegistro.put("grupo", grupo_);

                            // insertamos en la base
                db.insert("alumnos", null, nuevoRegistro);

                db.close();

                //mensaje en pantalla
                Toast.makeText(this, "REGISTRO EXITOSO EN BASE DE DATOS",
                        Toast.LENGTH_SHORT).show();



            } catch (SQLException e) {
                System.out.println(e);
                Toast.makeText(this, "BASE NO CREADA", Toast.LENGTH_SHORT).show();
            }

            */

        }

    }


}