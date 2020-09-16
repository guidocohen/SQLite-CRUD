package com.example.sqlite.actividades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.database.AlumnoCRUD
import com.example.sqlite.R
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*

class NuevoAlumnoActivity : AppCompatActivity() {
    val crud = AlumnoCRUD(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        bAdd.setOnClickListener {
            crud.newAlumno(
                Alumno(
                    etID.text.toString(),
                    etNombre.text.toString()
                )
            )
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}