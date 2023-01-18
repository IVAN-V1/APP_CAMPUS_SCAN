package com.example.aplicacion_campus_scam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aplicacion_campus_scam.db.BasedeDatos_alumnos;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class generador_QR extends AppCompatActivity {


    ImageView qr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generador_qr);


        qr = findViewById(R.id.imageView);



        // DATOS RECIBIDOS
        Bundle datosRecibidos = this.getIntent().getExtras();

        //VARIABLES PARA LAMACENAR LOS DATOS
        String nombre = datosRecibidos.getString("pNombre");
        String Rnum_compu =datosRecibidos.getString("pcompu");
        String Rfalla =datosRecibidos.getString("pfallas");

        try {
//GENERADOR DE QR
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            //todo los campos unidos EN UNO
            String todo = "NOMBRE: "+nombre
                    +"               NUMERO DE COMPU QUE USO:"+Rnum_compu
                    +"               FALLA QUE TIENE EL EQUIPO:"+ Rfalla;

            Bitmap bitmap = barcodeEncoder.encodeBitmap(todo, BarcodeFormat.QR_CODE, 750, 750);
            //MOSTRAR EL QR EN UN ELEMENTO IMAGENVIEW
            qr.setImageBitmap(bitmap);

            Toast.makeText(this, "QR GENERADO", Toast.LENGTH_SHORT).show();

        } catch (WriterException e) {
            e.printStackTrace();


        }

    }


    }
