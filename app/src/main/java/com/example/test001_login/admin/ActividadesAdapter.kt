package com.example.test001_login.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.Actividad

class ActividadesAdapter(
    private val items: List<Actividad>,
    private val onClick: (Actividad) -> Unit
) : RecyclerView.Adapter<ActividadesAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val card: CardView = v.findViewById(R.id.cardActividad)
        val tvNombre: TextView = v.findViewById(R.id.tvNombreActividad)
        val tvProfesor: TextView = v.findViewById(R.id.tvProfesorActividad)
        val tvHorario: TextView = v.findViewById(R.id.tvHorarioActividad)
        val tvPrecio: TextView = v.findViewById(R.id.tvPrecioActividad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actividad, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, position: Int) {
        val a = items[position]
        h.tvNombre.text = a.nombre
        h.tvProfesor.text = a.profesor
        h.tvHorario.text = a.diaHora
        h.tvPrecio.text = a.precio
        h.card.setOnClickListener { onClick(a) }
    }

    override fun getItemCount(): Int = items.size
}
