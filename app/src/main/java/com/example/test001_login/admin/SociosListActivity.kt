// app/src/main/java/com/example/test001_login/admin/SociosListActivity.kt
package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.Socio
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SociosListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socios_list)

        val rv = findViewById<RecyclerView>(R.id.rvSocios)
        rv.layoutManager = LinearLayoutManager(this)

        // Datos mock para la demo
        val datos = listOf(
            Socio(1, "Ana López", "36.123.456", "Activo"),
            Socio(2, "Juan Pérez", "30.987.654", "Vencido"),
            Socio(3, "María Díaz", "41.555.222", "Inactivo")
        )

        rv.adapter = SociosAdapter(datos) { socio ->
            // Al tocar un socio, abrimos el formulario en "modo edición" (mock)
            val i = Intent(this, SocioFormActivity::class.java)
            i.putExtra("socioNombre", socio.nombre)
            i.putExtra("socioDni", socio.dni)
            i.putExtra("socioEstado", socio.estado)
            startActivity(i)
        }

        // FAB: alta de socio (mock)
        findViewById<FloatingActionButton>(R.id.fabAgregar).setOnClickListener {
            startActivity(Intent(this, SocioFormActivity::class.java))
        }
    }
}
