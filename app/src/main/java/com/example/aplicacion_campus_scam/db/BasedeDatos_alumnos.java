package com.example.aplicacion_campus_scam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BasedeDatos_alumnos extends SQLiteOpenHelper {

	// string con la instruccion para crear la base de datos

	public BasedeDatos_alumnos(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// se crea la base de datos
		db.execSQL("CREATE TABLE alumnos(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT,matricula TEXT, carrera TEXT, grupo TEXT)");



	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {

		// En este ejemplo se pierden todos los datos de la tabla
		// se deber�a hacer un bkup
		 db.execSQL("DROP TABLE IF EXISTS alumnos");
	     db.execSQL("CREATE TABLE alumnos(id INTEGER PRIMARY KEY AUTOINCREMENT, codigo INTEGER, nombre TEXT)");
	}
}