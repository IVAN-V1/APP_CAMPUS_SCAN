package com.example.v3_.FRAGMENTOS;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.v3_.R;
import com.example.v3_.db.BASE_DE_DATOS_DE_REGISTROS;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class pdf extends Fragment {


    AutoCompleteTextView pdf_carrera;

    AutoCompleteTextView materias;

    AutoCompleteTextView de_grupos;

    String [] carreras ={"TICS EN DESARROLLO DE SOFTWARE ", "TICS EN ENTORNOS VIRTUALES"

            , "DESARROLLO DE NEGOCIOS"
    };


    String [] asignaturas ={" BASE DE DATOS  ", "APLICAIONES WEP"," FORMACION SOCIOCULTURAL"

            , "SISTEMAS OPERATIVOS"};


    String [] GRUPOS ={ "TID INMERSION",   " TID 101  ", "TID 201"," TID 301 ","TID 401","TID 501"

            , "TIE INMERSION " , "TIE 201"};



    EditText nombre, nombre_profesor, horario ;


    ImageButton crear;

    DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss z");

    String date = dateFormat.format(new Date());



    public pdf() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static pdf newInstance(String param1, String param2) {
        pdf fragment = new pdf();


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
        View view = inflater.inflate(R.layout.fragmento_pdf, container, false);


        nombre=view.findViewById(R.id.nombre_pdf);

        nombre_profesor=view.findViewById(R.id.nombre_completo);

        pdf_carrera=view.findViewById(R.id.completador_de_carreras);
        materias=view.findViewById(R.id.completador_de_materias);

        de_grupos=view.findViewById(R.id.completador_de_GRUPO);

        horario=view.findViewById(R.id.horarios);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1 , carreras);
        pdf_carrera.setAdapter(adapter);


        ArrayAdapter<String> adapter2= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1 , asignaturas);
        materias.setAdapter(adapter2);


        ArrayAdapter<String> adapter4= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1 , GRUPOS);
        de_grupos.setAdapter(adapter4);


        crear=view.findViewById(R.id.boton_crear);


        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VALIDACION DE LOS CAMPOS VACIOS
                String campo_pdf = nombre.getText().toString();
                String campo_car= pdf_carrera.getText().toString();
                String campo_mate= materias.getText().toString();
                String campo_gro= de_grupos.getText().toString();
                String campo_nom_pro= nombre_profesor.getText().toString().toUpperCase();
                String campo_hora= horario.getText().toString();


                // SIRVE PARA LOS TITULOS DE LAS COLUMNAS

                Font titulo_celda = FontFactory.getFont(FontFactory.COURIER, 12);

                Font celda = FontFactory.getFont(FontFactory.COURIER, 8);




                if (campo_pdf.equals("")|| campo_car.equals("")||campo_mate.equals("") || campo_gro.equals("") ||campo_nom_pro.equals("")||campo_hora.equals("") ) {

                    Toast.makeText(getContext(), "LLENAR LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }

                else {


                    try {



                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                                campo_pdf+".pdf");

                        //SI EL PDF YA EXISTE
                        if (file.exists()) {

                            Toast.makeText(getContext(), "PDF YA EXISTE EN"+file, Toast.LENGTH_LONG).show();

                        }
                        else {

                            //SIRVE PAEA CREAR EL ARCHIVO PDF
                            FileOutputStream archivo = new FileOutputStream(file);


                            Document documento = new Document(PageSize.A4);

                            PdfWriter.getInstance(documento, archivo);

                            documento.open();


                       /*

                            // Insertamos una imagen que se encuentra en los recursos de la
                            // application.
                            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),
                                    R.drawable.imagen_profesor);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            Image imagen = Image.getInstance(stream.toByteArray());
                            imagen.setAlignment(Element.ALIGN_LEFT);
                            documento.add(imagen);

*/

                            // SIRVE PARA MOSTRAR UN  TITULO EN EL PDF
                            Paragraph par1 = new Paragraph();
                            Font fontitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
                            par1.add(new Phrase("CARRERA :" + campo_car, fontitulo));
                            par1.setAlignment(Element.ALIGN_CENTER);
                            par1.add(new Phrase(Chunk.NEWLINE));
                            par1.add(new Phrase(Chunk.NEWLINE));
                            documento.add(par1);



                            // SIRVE PARA MOSTRAR UN  TITULO EN EL PDF
                            Paragraph par3 = new Paragraph();
                            Font fontitu = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
                            par3.add(new Phrase("REPORTE GENERADO POR EL PROFESOR : " + campo_nom_pro + " FECHA " + date, fontitu));
                            par3.setAlignment(Element.ALIGN_CENTER);
                            par3.add(new Phrase(Chunk.NEWLINE));
                            par3.add(new Phrase(Chunk.NEWLINE));
                            documento.add(par3);


                            // SIRVE PARA MOSTRAR UN  TITULO EN EL PDF
                            Paragraph par4 = new Paragraph();
                            Font fontitñ = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
                            par4.add(new Phrase("GRUPO: " + campo_gro, fontitñ));
                            par4.setAlignment(Element.ALIGN_CENTER);
                            par4.add(new Phrase(Chunk.NEWLINE));
                            par4.add(new Phrase(Chunk.NEWLINE));
                            documento.add(par4);


                            // SIRVE PARA MOSTRAR UN  TITULO EN EL PDF
                            Paragraph par5 = new Paragraph();
                            Font font = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK);
                            par5.add(new Phrase("HORARIO: " + campo_hora, fontitñ));
                            par5.setAlignment(Element.ALIGN_CENTER);
                            par5.add(new Phrase(Chunk.NEWLINE));
                            par5.add(new Phrase(Chunk.NEWLINE));
                            documento.add(par5);



                            //CREACION DE MI TABLA DE BASE DE DATOS
                            PdfPTable tabla = new PdfPTable(2);


                            // COLOR PARA MI TABLAS EN EL PDF
                            tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);

                            //COLOCAMOS EL NOMBRE QUE LLEVERA CADA COLUMNA
                            Paragraph tex1 = new Paragraph("ID", titulo_celda);
                            tex1.setAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(tex1);      //SE PUEDE COLOCAR LO QUE QUERAMOS

                            //COLOCAMOS EL NOMBRE QUE LLEVERA CADA COLUMNA
                            Paragraph tex2 = new Paragraph("REGISTROS", titulo_celda);
                            tex2.setAlignment(Element.ALIGN_CENTER);

                            tabla.addCell(tex2);      //SE PUEDE COLOCAR LO QUE QUERAMOS


                            // BASE DE DATOS
                            BASE_DE_DATOS_DE_REGISTROS admin = new BASE_DE_DATOS_DE_REGISTROS(getContext(), "REGISTROS", null, 2);
                            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();


                            // rawQuery es un select en esta base de datos
                            Cursor fila = baseDeDatos.rawQuery("select * from registros ", null);

                            //el metodo moveToFirst revisa si la consulta tiene valores
                            if (fila.moveToFirst()) {

                                do {

                                    Paragraph id_ = new Paragraph(fila.getString(0), celda);
                                    id_.setAlignment(Element.ALIGN_JUSTIFIED);
                                    //-----
                                    Paragraph nombre_ = new Paragraph(fila.getString(1), celda);
                                    nombre_.setAlignment(Element.ALIGN_JUSTIFIED);


                                    tabla.addCell(id_);
                                    tabla.addCell(nombre_);

                                } while (fila.moveToNext());

                            }

                            documento.add(tabla);
                            documento.close();

                            Toast.makeText(getContext(), "PDF CREADO EN"+file, Toast.LENGTH_SHORT).show();



                        }
                    } catch (Exception e) {


                        Toast.makeText(getContext(), "PDF NO CREADO"+e , Toast.LENGTH_LONG).show();

                    }


                }




            }
        });

        return view;
    }



}