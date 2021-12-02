package com.grupo2.appserviexpress

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQliteHelper(context: Context) :SQLiteOpenHelper(
    context,"mensajeros.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreation = "CREATE TABLE clientes " + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT,tipo_de_servicio TEXT,direccion_de_entrega TEXT,telefono TEXT,valor TEXT )"
        db!!.execSQL(ordenCreation)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado= "DROP TABLE IF EXISTS clientes"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }
    fun agregarDato(
        nombre:String,
        tipo_de_servicio:String,
        direccion_de_entrega:String,
        telefono: String,
        valor: String
    ){
        val datos = ContentValues()
        datos.put("nombre",nombre)
        datos.put("tipo_de_servicio",tipo_de_servicio)
        datos.put("direccion_de_entrega",direccion_de_entrega)
        datos.put("telefono",telefono)
        datos.put("valor",valor)
        val db = this.writableDatabase
        db.insert("clientes",null,datos)
        db.close()



    }


}