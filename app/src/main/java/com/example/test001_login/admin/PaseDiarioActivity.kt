package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.data.PaseDiarioDao
import com.example.test001_login.model.PaseDiario
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaseDiarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pase_diario)

        val etDni = findViewById<EditText>(R.id.etDniNoSocio)
        val etMonto = findViewById<EditText>(R.id.etMontoPase)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarPase)

        val dao = PaseDiarioDao(this)

        btnRegistrar.setOnClickListener {

            val dni = etDni.text.toString().trim()
            val montoStr = etMonto.text.toString().trim()

            // Validación 1: campos vacíos
            if (dni.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación 2: monto numérico
            val monto = montoStr.toDoubleOrNull()
            if (monto == null) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Fecha actual
            val fecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val pase = PaseDiario(
                id = null,   // autoincrement
                dni = dni,
                fecha = fecha,
                monto = monto
            )

            val resultado = dao.registrarPase(pase)

            if (resultado > 0) {
                Toast.makeText(this, "Pase registrado correctamente", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar el pase", Toast.LENGTH_LONG).show()
            }
        }
    }
}
