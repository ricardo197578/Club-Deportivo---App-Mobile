package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.data.SocioRepository
import com.example.test001_login.model.Socio
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SociosListActivity : AppCompatActivity() {

    private lateinit var repo: SocioRepository
    private lateinit var rv: RecyclerView
    private lateinit var adapter: SociosAdapter      //  Reutilizamos adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socios_list)

        repo = SocioRepository(this)

        rv = findViewById(R.id.rvSocios)
        rv.layoutManager = LinearLayoutManager(this)

        // Inicializamos adapter vacío
        adapter = SociosAdapter(emptyList()) { socio ->
            val i = Intent(this, SocioFormActivity::class.java)
            i.putExtra("modo", "edicion")
            i.putExtra("socioDni", socio.dni)
            startActivity(i)
        }
        rv.adapter = adapter

        // FAB abrir formulario para ALTA
        findViewById<FloatingActionButton>(R.id.fabAgregar).setOnClickListener {
            val i = Intent(this, SocioFormActivity::class.java)
            i.putExtra("modo", "alta")
            startActivity(i)
        }

        cargarLista()
    }
        //Usamos onResume para que la lista se actualice automaticamente  cuando creamos,editamos o eliminamos un socio
    override fun onResume() {
        super.onResume()
        cargarLista()
    }
        //SociosListActivity con el siguiente codigo carga los socios desde SQLite
    private fun cargarLista() {
        val lista = repo.getAllSocios()

        // Actualizamos datos del adapter
        adapter = SociosAdapter(lista) { socio ->
            val i = Intent(this, SocioFormActivity::class.java)
            i.putExtra("modo", "edicion")
            i.putExtra("socioDni", socio.dni)
            startActivity(i)
        }

        rv.adapter = adapter
    }
}

