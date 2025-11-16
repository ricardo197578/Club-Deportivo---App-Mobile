package com.example.test001_login.admin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.data.NoSocioRepository
import com.example.test001_login.model.NoSocio
import java.text.SimpleDateFormat
import java.util.*

class NoSocioFormActivity : AppCompatActivity() {

    private var modoEdicion = false
    private var dniOriginal: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_socio_form)

        val etNombre = findViewById<EditText>(R.id.etNombreNoSocio)
        val etApellido = findViewById<EditText>(R.id.etApellidoNoSocio)
        val etDni = findViewById<EditText>(R.id.etDniNoSocio)
        val etTelefono = findViewById<EditText>(R.id.etTelefonoNoSocio)

        val btnGuardar = findViewById<Button>(R.id.btnGuardarNoSocio)
        val btnCancelar = findViewById<Button>(R.id.btnCancelarNoSocio)
        val btnEliminar = findViewById<Button>(R.id.btnEliminarNoSocio)

        val repo = NoSocioRepository(this)

        // ¿VIENE A EDITAR?
        val dniRecibido = intent.getStringExtra("dni")
        if (dniRecibido != null) {
            modoEdicion = true
            dniOriginal = dniRecibido

            val noSocio = repo.getNoSocioByDni(dniRecibido)
            noSocio?.let {
                etNombre.setText(it.nombre)
                etApellido.setText(it.apellido)
                etDni.setText(it.dni)
                etDni.isEnabled = false
                etTelefono.setText(it.telefono)
            }

            btnEliminar.visibility = View.VISIBLE
        } else {
            btnEliminar.visibility = View.GONE
        }

        // GUARDAR
        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val dni = etDni.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Fecha de registro SOLO para nuevos
            val fechaHoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val modelo = NoSocio(
                id = null,
                nombre = nombre,
                apellido = apellido,
                dni = dni,
                telefono = telefono,
                fechaRegistro = fechaHoy
            )


            val ok: Boolean = if (modoEdicion) {
                repo.updateNoSocio(modelo)
            } else {
                repo.insertNoSocio(modelo)
            }


            if (ok) {
                Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_LONG).show()
            }
        }

        // CANCELAR
        btnCancelar.setOnClickListener { finish() }

        // ELIMINAR
        btnEliminar.setOnClickListener {
            dniOriginal?.let {
                repo.deleteNoSocio(it)
                Toast.makeText(this, "No socio eliminado", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
