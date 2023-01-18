package com.example.v3_.FRAGMENTOS;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import com.example.v3_.R;
import com.example.v3_.db.BASE_DE_DATOS_DE_REGISTROS;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;


public class HOME extends Fragment {


    EditText ESCANEADO;

    ListView listView;
    private List<String> mlista = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    public HOME() {// Required empty public constructor
    }

    public static HOME newInstance(String param1, String param2) {
        HOME fragment = new HOME();

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
        View view = inflater.inflate(R.layout.fragment_h_o_m_e, container, false);


        ESCANEADO =view.findViewById(R.id.RESULTADO_ESCANEAR);

        ImageButton escan=view.findViewById(R.id.escanear);



        // para ESCANEAR
        final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
                result -> {
                    if (result.getContents() == null) {
                        Toast.makeText(getContext(), "CALCELADO", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext(), "REGISTRO EXITOSO: " , Toast.LENGTH_SHORT).show();
                        ESCANEADO.setText(result.getContents());


                        try {

                            String nom_ = ESCANEADO.getText().toString().toUpperCase();


                            // GUARDAR INFOMACION EN LA BASE DE DATOS

                            BASE_DE_DATOS_DE_REGISTROS base = new BASE_DE_DATOS_DE_REGISTROS(getContext(), "REGISTROS", null, 2);
                            // nombre de la base de datos
                            SQLiteDatabase db = base.getWritableDatabase();

                            // DATOS A PASAR EN LA BASE DE DATOS
                            ContentValues registro = new ContentValues();
                            registro.put("registros", nom_);


                            //ISERTA A BASE DE DATOS
                            db.insert("registros", null, registro);
                            //CERRAR BASE DE DATOS
                            db.close();

                            //mensaje en pantalla
                            Toast.makeText(getContext(), "REGISTRO EXITOSO EN BASE DE DATOS",
                                    Toast.LENGTH_SHORT).show();

                            ESCANEADO.setText("");


                            mlista.add(nom_);

                            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mlista);
                            listView.setAdapter(adapter);


                        } catch (Exception e) {

                            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();

                        }

                    }
                });



        escan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               ScanOptions options = new ScanOptions();
               options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
               options.setPrompt("ESCANEA EL CODIGO");
               options.setCameraId(0);  // Use a specific camera of the device
               options.setBeepEnabled(false);
               options.setOrientationLocked(false);
               options.setBarcodeImageEnabled(false);
               options.setBeepEnabled(true); //sonido a escanear
               options.setCaptureActivity(CaptureActivityPortraint.class);
               barcodeLauncher.launch(options);



           }
       });



        return view;
    }



}