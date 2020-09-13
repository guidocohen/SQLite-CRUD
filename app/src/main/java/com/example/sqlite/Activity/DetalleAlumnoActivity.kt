package com.example.sqlite.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.Modelo.Alumno
import com.example.sqlite.SQLite.AlumnoCRUD
import com.example.sqlite.R
import kotlinx.android.synthetic.main.activity_detalle_alumno.*

class DetalleAlumnoActivity : AppCompatActivity() {
    val crud = AlumnoCRUD(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_alumno)

        val id = intent.getStringExtra("ID")
        val alumno = crud.getAlumno(id!!)
        etID.setText(alumno.id, TextView.BufferType.EDITABLE)
        etNombre.setText(alumno.nombre, TextView.BufferType.EDITABLE)

        bActualizar.setOnClickListener {
            crud.updateAlumno(
                Alumno(
                    etID.text.toString(),
                    etNombre.text.toString()
                )
            )
            startActivity(Intent(this, MainActivity::class.java))
        }

        bEliminar.setOnClickListener {
            crud.deleteAlumno(alumno)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
