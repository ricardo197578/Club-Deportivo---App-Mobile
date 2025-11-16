package com.example.test001_login.admin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.data.SocioRepository
import com.example.test001_login.model.Socio
import com.google.android.material.textfield.TextInputEditText
import android.widget.AutoCompleteTextView
import java.text.SimpleDateFormat
import java.util.*

class SocioFormActivity : AppCompatActivity() {

    private lateinit var repo: SocioRepository
    private var modoEdicion = false
    private var socioOriginal: Socio? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socio_form)

        repo = SocioRepository(this)

        // Inputs
        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val etApellido = findViewById<TextInputEditText>(R.id.etApellido)
        val etDni = findViewById<TextInputEditText>(R.id.etDni)
        val etTelefono = findViewById<TextInputEditText>(R.id.etTelefono)
        val etDireccion = findViewById<TextInputEditText>(R.id.etDireccion)
        val etUltimoPago = findViewById<TextInputEditText>(R.id.etUltimoPago)
        val ddEstado = findViewById<AutoCompleteTextView>(R.id.ddEstado)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)

        // Estado dropdown
        val estados = listOf("Activo", "Inactivo", "Vencido")
        ddEstado.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, estados))

        // Revisar si viene DNI para editar
        val dniRecibido = intent.getStringExtra("socioDni")

        if (dniRecibido != null) {
            modoEdicion = true
            socioOriginal = repo.getSocioByDni(dniRecibido)

            socioOriginal?.let {
                etNombre.setText(it.nombre)
                etApellido.setText(it.apellido)
                etDni.setText(it.dni)
                etDni.isEnabled = false

                etTelefono.setText(it.telefono)
                etDireccion.setText(it.direccion)
                etUltimoPago.setText(it.fechaUltimoPago ?: "")

                ddEstado.setText(
                    when (it.activo) {
                        1 -> "Activo"
                        0 -> "Inactivo"
                        2 -> "Vencido"
                        else -> "Activo"
                    },
                    false
                )
            }

            btnEliminar.visibility = Button.VISIBLE

        } else {
            // Alta → ocultar eliminar
            btnEliminar.visibility = Button.GONE
        }

        // BOTÓN GUARDAR
        findViewById<Button>(R.id.btnGuardar).setOnClickListener {

            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val dni = etDni.text.toString()
            val telefono = etTelefono.text.toString()
            val direccion = etDireccion.text.toString()
            val ultimoPagoTxt = etUltimoPago.text.toString()
            val estadoTxt = ddEstado.text.toString()

            if (nombre.isEmpty() || dni.isEmpty()) {
                Toast.makeText(this, "Completar nombre y DNI", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val activo = when (estadoTxt) {
                "Activo" -> 1
                "Inactivo" -> 0
                "Vencido" -> 2
                else -> 1
            }

            val fechaHoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            if (!modoEdicion) {
                // CREACIÓN NUEVO SOCIO
                val socio = Socio(
                    id = 0,
                    dni = dni,
                    nombre = nombre,
                    apellido = apellido,
                    telefono = telefono,
                    direccion = direccion,
                    fechaAlta = fechaHoy,               // ✔ SIEMPRE NO NULO
                    fechaUltimoPago = ultimoPagoTxt.ifEmpty { null },
                    activo = activo
                )

                if (repo.insertSocio(socio)) {
                    Toast.makeText(this, "Socio creado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error: DNI ya existe", Toast.LENGTH_SHORT).show()
                }

            } else {
                // EDICIÓN
                val orig = socioOriginal!!

                val socio = Socio(
                    id = orig.id,
                    dni = orig.dni,                      // ✔ No se modifica
                    nombre = nombre,
                    apellido = apellido,
                    telefono = telefono,
                    direccion = direccion,
                    fechaAlta = orig.fechaAlta,          // ✔ Mantener
                    fechaUltimoPago = ultimoPagoTxt.ifEmpty { orig.fechaUltimoPago },
                    activo = activo
                )

                if (repo.updateSocio(socio)) {
                    Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // BOTÓN CANCELAR
        findViewById<Button>(R.id.btnCancelar).setOnClickListener { finish() }

        // BOTÓN ELIMINAR
        btnEliminar.setOnClickListener {
            val orig = socioOriginal ?: return@setOnClickListener
            repo.deleteSocio(orig.dni)
            Toast.makeText(this, "Socio eliminado", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
