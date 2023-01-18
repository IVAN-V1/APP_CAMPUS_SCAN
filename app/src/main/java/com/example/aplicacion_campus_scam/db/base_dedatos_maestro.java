package com.example.aplicacion_campus_scam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class base_dedatos_maestro extends SQLiteOpenHelper {


    public base_dedatos_maestro(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //AQUI CREAMOS LA TABLA
        db.execSQL("CREATE TABLE maestros (id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, numero_de_trabajador TEXT,carrera_impartida TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {

        db.execSQL("DROP TABLE maestros");
        db.execSQL("CREATE TABLE maestros (id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, numero_de_trabajador TEXT,carrera_impartida TEXT )");

    }
}
