package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.NoSocio
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoSociosListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_no_socios_list)

        val rv = findViewById<RecyclerView>(R.id.rvNoSocios)
        rv.layoutManager = LinearLayoutManager(this)

        // Datos mock
        val datos = listOf(
            NoSocio(1, "Carlos Gómez", "32.111.222", "11-5555-1111"),
            NoSocio(2, "Lucía Méndez", "40.222.333", "11-5555-2222"),
            NoSocio(3, "Pablo Ruiz",   "28.444.555", "11-5555-3333")
        )

        rv.adapter = NoSociosAdapter(datos) { item ->
            val i = Intent(this, NoSocioFormActivity::class.java)
            i.putExtra("nombre", item.nombre)
            i.putExtra("dni", item.dni)
            i.putExtra("telefono", item.telefono)
            startActivity(i)
        }

        findViewById<FloatingActionButton>(R.id.fabAgregarNoSocio).setOnClickListener {
            startActivity(Intent(this, NoSocioFormActivity::class.java)) // alta
        }
    }
}
