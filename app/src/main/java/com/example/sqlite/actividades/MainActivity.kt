package com.example.sqlite.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.*
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.recyclerView.AdaptadorCustom
import com.example.sqlite.recyclerView.ClickListener
import com.example.sqlite.recyclerView.LongClickListener
import com.example.sqlite.database.AlumnoCRUD
import com.example.sqlite.modelo.Alumnos
import com.example.sqlite.utilidades.HttpResponse
import com.example.sqlite.utilidades.Network
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var crud: AlumnoCRUD
    lateinit var alumnos: ArrayList<Alumno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista.setHasFixedSize(true)
        lista.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            startActivity(Intent(this, NuevoAlumnoActivity::class.java))
        }

        val network = Network(this)
        val activity = applicationContext
        val gson = Gson()

        crud = AlumnoCRUD(this)
        alumnos = crud.getAlumnos()

        network.httpRequest(
            activity,
            "http://192.168.56.1/alumnos/",
            object : HttpResponse {
                override fun httpResponseSuccess(response: String) {
                    Log.d("response", response)

                    val alumnosAPI = gson.fromJson(response, Alumnos::class.java).items

                    alumnos.forEach { crud.deleteAlumno(it) }
                    alumnosAPI.forEach { crud.newAlumno(Alumno(it.id, it.nombre)) }

                    /*for (alumno in alumnos) {
                        crud.deleteAlumno(alumno)
                    }*/
                    /*for (alumno in alumnosAPI) {
                        crud.newAlumno(Alumno(alumno.id, alumno.nombre))
                    }*/

                    alumnos = crud.getAlumnos()
                    configurarAdaptador(alumnos)
                }

                override fun httpErrorResponse(response: String) {
                    Toast.makeText(activity, "Error al hacer la solicitud HTTP", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    fun configurarAdaptador(data: ArrayList<Alumno>) {
        lista.adapter = AdaptadorCustom(
            data,
            object : ClickListener {
                override fun onClick(vista: View, index: Int) {
                    val intent = Intent(applicationContext, DetalleAlumnoActivity::class.java)
                    intent.putExtra("ID", data[index].id)
                    startActivity(intent)
                }
            },
            object : LongClickListener {
                override fun longClick(vista: View, index: Int) {
                    // longclick
                }
            })
    }
}