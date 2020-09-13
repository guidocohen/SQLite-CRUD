package com.example.sqlite.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.*
import com.example.sqlite.Modelo.Alumno
import com.example.sqlite.RecyclerView.AdaptadorCustom
import com.example.sqlite.RecyclerView.ClickListener
import com.example.sqlite.RecyclerView.LongClickListener
import com.example.sqlite.SQLite.AlumnoCRUD
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