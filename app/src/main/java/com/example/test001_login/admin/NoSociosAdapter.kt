package com.example.test001_login.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.NoSocio

class NoSociosAdapter(
    private val items: List<NoSocio>,
    private val onClick: (NoSocio) -> Unit
) : RecyclerView.Adapter<NoSociosAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.cardNoSocio)
        val tvNombre: TextView = view.findViewById(R.id.tvNombreNoSocio)
        val tvDni: TextView = view.findViewById(R.id.tvDniNoSocio)
        val tvTelefono: TextView = view.findViewById(R.id.tvTelNoSocio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_no_socio, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val n = items[position]
        holder.tvNombre.text = n.nombre
        holder.tvDni.text = n.dni
        holder.tvTelefono.text = n.telefono
        holder.card.setOnClickListener { onClick(n) }
    }

    override fun getItemCount(): Int = items.size
}
