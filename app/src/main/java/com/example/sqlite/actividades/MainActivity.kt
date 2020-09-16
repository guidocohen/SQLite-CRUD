package com.example.sqlite.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.*
import com.example.sqlite.modelo.Alumno
import com.example.sqlite.recyclerView.AdaptadorCustom
import com.example.sqlite.recyclerView.ClickListener
import com.example.sqlite.recyclerView.LongClickListener
import com.example.sqlite.database.AlumnoCRUD
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var crud: AlumnoCRUD
    lateinit var alumnos: ArrayList<Alumno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crud = AlumnoCRUD(this)
        alumnos = crud.getAlumnos()

        lista.setHasFixedSize(true)
        lista.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            startActivity(Intent(this, NuevoAlumnoActivity::class.java))
        }

        lista.adapter = AdaptadorCustom(
            alumnos,
            object : ClickListener {
                override fun onClick(vista: View, index: Int) {
                    val intent = Intent(applicationContext, DetalleAlumnoActivity::class.java)
                    intent.putExtra("ID", alumnos[index].id)
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