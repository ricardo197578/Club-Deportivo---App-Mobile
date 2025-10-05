package com.example.test001_login.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.Profesor

class ProfesoresAdapter(
    private val items: List<Profesor>,
    private val onClick: (Profesor) -> Unit
) : RecyclerView.Adapter<ProfesoresAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val card: CardView = v.findViewById(R.id.cardProfesor)
        val tvNombre: TextView = v.findViewById(R.id.tvNombreProfesor)
        val tvEspecialidad: TextView = v.findViewById(R.id.tvEspecialidadProfesor)
        val tvEmail: TextView = v.findViewById(R.id.tvEmailProfesor)
        val tvTel: TextView = v.findViewById(R.id.tvTelProfesor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profesor, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, position: Int) {
        val p = items[position]
        h.tvNombre.text = p.nombre
        h.tvEspecialidad.text = p.especialidad
        h.tvEmail.text = p.email
        h.tvTel.text = p.telefono
        h.card.setOnClickListener { onClick(p) }
    }

    override fun getItemCount(): Int = items.size
}
