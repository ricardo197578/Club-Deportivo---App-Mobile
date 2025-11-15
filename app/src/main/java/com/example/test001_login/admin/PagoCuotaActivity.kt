package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.data.PaymentDao
import com.example.test001_login.model.Payment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PagoCuotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_cuota)

        val etDni = findViewById<EditText>(R.id.etDniSocio)
        val etMonto = findViewById<EditText>(R.id.etMontoCuota)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarPago)

        val dao = PaymentDao(this)

        btnRegistrar.setOnClickListener {

            val dni = etDni.text.toString().trim()
            val montoStr = etMonto.text.toString().trim()

            // Validación 1
            if (dni.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación 2: monto válido
            val monto = montoStr.toDoubleOrNull()
            if (monto == null) {
                Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Fecha del pago
            val fechaHoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val pago = Payment(
                id = null,         // autoincrement
                socioDni = dni,
                fechaPago = fechaHoy,
                monto = monto
            )

            val resultado = dao.registrarPago(pago)

            if (resultado > 0) {
                Toast.makeText(this, "Pago registrado correctamente", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar el pago", Toast.LENGTH_LONG).show()
            }
        }
    }
}
