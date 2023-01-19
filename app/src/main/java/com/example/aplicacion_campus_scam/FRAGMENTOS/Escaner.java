package com.example.aplicacion_campus_scam.FRAGMENTOS;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aplicacion_campus_scam.R;
import com.example.aplicacion_campus_scam.Base_de_datos.BASE_DE_DATOS_DE_REGISTROS;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;


public class Escaner extends Fragment {



    EditText ESCANEADO;

    ListView listView;

    ImageButton escan;

    private List<String> mlista = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    private static final int REQUEST_CODE_QR_SCAN = 101;
    private final String LOGTAG = "QRCScanner-PANTALLA_DE_INICIO";

    public Escaner() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static Escaner newInstance(String param1, String param2) {
        Escaner fragment = new Escaner();

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
       View view= inflater.inflate(R.layout.fragment_escaner, container, false);

        ESCANEADO =view.findViewById(R.id.RESULTADO_ESCANEAR);

         escan=view.findViewById(R.id.escanear);



        // para ESCANEAR
        final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
                result -> {
                    if (result.getContents() == null) {

                        Toast.makeText(getContext(),"CALCELADO", Toast.LENGTH_LONG).show();

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