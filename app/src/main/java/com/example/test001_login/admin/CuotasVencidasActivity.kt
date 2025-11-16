package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.data.PaymentDao
import com.example.test001_login.data.SocioRepository
import java.text.SimpleDateFormat
import java.util.*

class CuotasVencidasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuotas_vencidas)

        val rv = findViewById<RecyclerView>(R.id.rvCuotasVencidas)
        rv.layoutManager = LinearLayoutManager(this)

        val fechaHoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val pagoDao = PaymentDao(this)
        val socioRepo = SocioRepository(this)

        // A) Socios que sí tienen pagos, pero atrasados
        val vencidosPagaron = pagoDao.sociosConCuotaVencida(fechaHoy)

        // B) Socios que nunca pagaron → automáticamente vencidos
        val sinPagos = pagoDao.sociosSinPagos()

        // C) Combinar todo
        val listaFinal = (vencidosPagaron + sinPagos).distinct()

        if (listaFinal.isEmpty()) {
            Toast.makeText(this, "No hay cuotas vencidas", Toast.LENGTH_LONG).show()
        }

        // Convertir DNIs a texto para mostrar
        val listaMostrar = listaFinal.mapNotNull { dni ->
            val socio = socioRepo.getSocioByDni(dni)
            socio?.let {
                "DNI: ${it.dni} - ${it.nombre} ${it.apellido}"
            }
        }

        rv.adapter = CuotasVencidasAdapter(listaMostrar)
    }
}
