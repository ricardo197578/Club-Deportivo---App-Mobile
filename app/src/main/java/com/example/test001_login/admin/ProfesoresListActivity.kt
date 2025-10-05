package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.Profesor
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfesoresListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profesores_list)

        val rv = findViewById<RecyclerView>(R.id.rvProfesores)
        rv.layoutManager = LinearLayoutManager(this)

        // Mock
        val datos = listOf(
            Profesor(1, "Sofía Romero", "Yoga",        "sofia@yoga.com",    "11-6000-1111"),
            Profesor(2, "Martín Pérez", "CrossFit",    "martin@cross.com",  "11-6000-2222"),
            Profesor(3, "Carla Díaz",   "Spinning",    "carla@spin.com",    "11-6000-3333")
        )

        rv.adapter = ProfesoresAdapter(datos) { prof ->
            val i = Intent(this, ProfesorFormActivity::class.java)
            i.putExtra("nombre", prof.nombre)
            i.putExtra("especialidad", prof.especialidad)
            i.putExtra("email", prof.email)
            i.putExtra("telefono", prof.telefono)
            startActivity(i)
        }

        findViewById<FloatingActionButton>(R.id.fabAgregarProfesor).setOnClickListener {
            startActivity(Intent(this, ProfesorFormActivity::class.java))
        }
    }
}
