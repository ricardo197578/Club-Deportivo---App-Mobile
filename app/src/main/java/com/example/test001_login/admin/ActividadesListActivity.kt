package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.Actividad
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActividadesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividades_list)

        val rv = findViewById<RecyclerView>(R.id.rvActividades)
        rv.layoutManager = LinearLayoutManager(this)

        // Mock
        val datos = listOf(
            Actividad(1, "CrossFit", "Martín Ortiz", "Lun 19:00", "$15.000"),
            Actividad(2, "Yoga",     "Laura Díaz",   "Mar 08:00", "$12.000"),
            Actividad(3, "Spinning", "Carla Vivas",  "Jue 20:00", "$14.000")
        )

        rv.adapter = ActividadesAdapter(datos) { act ->
            val i = Intent(this, ActividadFormActivity::class.java)
            i.putExtra("nombre", act.nombre)
            i.putExtra("profesor", act.profesor)
            i.putExtra("diaHora", act.diaHora)
            i.putExtra("precio", act.precio)
            startActivity(i)
        }

        findViewById<FloatingActionButton>(R.id.fabAgregarActividad).setOnClickListener {
            startActivity(Intent(this, ActividadFormActivity::class.java))
        }
    }
}
