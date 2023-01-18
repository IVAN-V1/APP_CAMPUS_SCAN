package com.example.aplicacion_campus_scam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BASE_DE_DATOS_DE_REGISTROS extends SQLiteOpenHelper {


    public BASE_DE_DATOS_DE_REGISTROS(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // se crea la base de datos
        db.execSQL("CREATE TABLE registros(id INTEGER PRIMARY KEY AUTOINCREMENT, registros TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE registros");
        db.execSQL("CREATE TABLE maestros (id INTEGER PRIMARY KEY AUTOINCREMENT, registros TEXT  )");

    }
}
