package com.example.sqlite.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.database.AlumnoCRUD
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
