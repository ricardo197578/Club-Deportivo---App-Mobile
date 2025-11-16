package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.data.NoSocioRepository
import com.example.test001_login.model.NoSocio
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoSociosListActivity : AppCompatActivity() {

    private lateinit var repo: NoSocioRepository
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_socios_list)

        repo = NoSocioRepository(this)

        rv = findViewById(R.id.rvNoSocios)
        rv.layoutManager = LinearLayoutManager(this)

        findViewById<FloatingActionButton>(R.id.fabAgregarNoSocio).setOnClickListener {
            startActivity(Intent(this, NoSocioFormActivity::class.java))
        }

        cargarLista()
    }

    override fun onResume() {
        super.onResume()
        cargarLista()
    }

    private fun cargarLista() {
        val lista = repo.getAllNoSocios()
        rv.adapter = NoSociosAdapter(lista) { noSocio ->
            val i = Intent(this, NoSocioFormActivity::class.java)
            i.putExtra("dni", noSocio.dni)
            startActivity(i)
        }
    }
}

