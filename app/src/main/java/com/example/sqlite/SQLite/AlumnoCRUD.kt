package com.example.sqlite.SQLite

import android.content.ContentValues
import android.content.Context
import com.example.sqlite.Modelo.Alumno

class AlumnoCRUD(context: Context) {
    private var helper = DataBaseHelper(context)

    fun newAlumno(item: Alumno) {
        // Abrir la BD en modo escritura
        val db = helper.writableDatabase

        // Mapeo de columnas con valores a insertar
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        // Insertar una nueva fila en la tabla
        db.insert(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            null, values
        )

        // Cerrar DB
        db.close()
    }

    fun getAlumnos(): ArrayList<Alumno> {
        val items = ArrayList<Alumno>()

        // Abrir DB en modo lectura
        val db = helper.readableDatabase

        // Especificar columnas que quiero consultar
        val columnas = arrayOf(
            AlumnosContract.Companion.Entrada.COLUMNA_ID,
            AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        // Crear un cursor para recorrer la tabla
        val cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas, null, null, null, null, null
        )

        // Hacer el recorrido del cursor en la tabla
        while (cursor.moveToNext()) {
            items.add(
                Alumno(
                    cursor.getString(cursor.getColumnIndex(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                    cursor.getString(cursor.getColumnIndex(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
                )
            )
        }

        // Cerrar cursor y DB
        cursor.close()
        db.close()

        return items
    }

    fun getAlumno(id: String): Alumno {
        lateinit var item: Alumno

        // Abrir DB en modo lectura
        val db = helper.readableDatabase

        // Especificar columnas que quiero consultar
        val columnas = arrayOf(
            AlumnosContract.Companion.Entrada.COLUMNA_ID,
            AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        // Crear un cursor para recorrer la tabla
        val cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas, "id = ?", arrayOf(id), null, null, null
        )

        // Hacer el recorrido del cursor en la tabla
        while (cursor.moveToNext()) {
            item = Alumno(
                cursor.getString(cursor.getColumnIndex(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                cursor.getString(cursor.getColumnIndex(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
            )
        }

        // Cerrar cursor y DB
        cursor.close()
        db.close()

        return item
    }

    fun updateAlumno(item: Alumno) {
        // Abrir la BD en modo escritura
        val db = helper.writableDatabase

        // Mapeo de columnas con valores a insertar
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        // Modificar una fila en la tabla
        db.update(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id = ?",
            arrayOf(item.id)
        )

        // Cerrar DB
        db.close()
    }

    fun deleteAlumno(item: Alumno) {
        // Abrir la BD en modo escritura
        val db = helper.writableDatabase

        // Modificar una fila en la tabla
        db.delete(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            "id = ?",
            arrayOf(item.id)
        )

        // Cerrar DB
        db.close()
    }
}