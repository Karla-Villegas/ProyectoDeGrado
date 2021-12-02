package com.example.proyectogrado2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(contex: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(contex, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table tareas(id Int primary key, titulo String, descripcion String, hora String, fecha String, categoria String)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}