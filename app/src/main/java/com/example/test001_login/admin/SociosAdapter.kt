// app/src/main/java/com/example/test001_login/admin/SociosAdapter.kt
package com.example.test001_login.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.Socio

class SociosAdapter(
    private val items: List<Socio>,
    private val onClick: (Socio) -> Unit
) : RecyclerView.Adapter<SociosAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.cardSocio)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvDni: TextView = view.findViewById(R.id.tvDni)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_socio, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val s = items[position]
        holder.tvNombre.text = s.nombre
        holder.tvDni.text = s.dni
        holder.tvEstado.text = s.estado
        holder.card.setOnClickListener { onClick(s) }
    }

    override fun getItemCount(): Int = items.size
}
