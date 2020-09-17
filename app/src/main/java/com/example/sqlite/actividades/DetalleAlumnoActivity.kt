package com.example.sqlite.actividades

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.database.AlumnoCRUD
import com.example.sqlite.R
import com.example.sqlite.modelo.HttpAPIResponse
import com.example.sqlite.utilidades.HttpResponse
import com.example.sqlite.utilidades.Network
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detalle_alumno.*
import kotlinx.android.synthetic.main.activity_detalle_alumno.etID
import kotlinx.android.synthetic.main.activity_detalle_alumno.etNombre
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*

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
            //crud.updateAlumno(Alumno(etID.text.toString(),etNombre.text.toString()))
            clickEvent("actualizaralumno")
        }

        bEliminar.setOnClickListener {
            //crud.deleteAlumno(alumno)
            clickEvent("eliminaralumno")
        }
    }

    fun clickEvent(operacion: String) {
        val context = applicationContext

        val query = "?id=" + etID.text.toString() + "&nombre=" + etNombre.text.toString()
        val url = "http://192.168.56.1/alumnos/$operacion$query"

        Network(this).httpRequest(context, url, object : HttpResponse {
            override fun httpResponseSuccess(response: String) {
                val message = Gson().fromJson(response, HttpAPIResponse::class.java)
                Toast.makeText(context, message.response, Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, MainActivity::class.java))
            }

            override fun httpErrorResponse(response: String) {
                Log.e("error response", response)
                Toast.makeText(
                    context, "Hubo un problema al enviar la solicitud",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
