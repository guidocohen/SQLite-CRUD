package com.example.sqlite.actividades

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.database.AlumnoCRUD
import com.example.sqlite.R
import com.example.sqlite.modelo.HttpAPIResponse
import com.example.sqlite.utilidades.HttpResponse
import com.example.sqlite.utilidades.Network
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*

class NuevoAlumnoActivity : AppCompatActivity() {
    val crud = AlumnoCRUD(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        bAdd.setOnClickListener {
            //crud.newAlumno(Alumno(etID.text.toString(),etNombre.text.toString()))

            val context = applicationContext
            val network = Network(this)
            val query = "?id=" + etID.text.toString() + "&nombre=" + etNombre.text.toString()
            val url = "http://192.168.56.1/alumnos/nuevoalumno$query"

            network.httpRequest(context, url, object : HttpResponse {
                override fun httpResponseSuccess(response: String) {
                    val gson = Gson()
                    val message = gson.fromJson(response, HttpAPIResponse::class.java)
                    Toast.makeText(context, message.response, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                }

                override fun httpErrorResponse(response: String) {
                    Log.e("error response", response)
                    Toast.makeText(
                        context,
                        "Hubo un problema al enviar la solicitud",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}