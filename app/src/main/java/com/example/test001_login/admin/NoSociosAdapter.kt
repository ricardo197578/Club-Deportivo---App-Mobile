package com.example.test001_login.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R
import com.example.test001_login.model.NoSocio

class NoSociosAdapter(
    private val lista: List<NoSocio>,
    private val onClick: (NoSocio) -> Unit
) : RecyclerView.Adapter<NoSociosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreNoSocio)
        val tvDni: TextView = view.findViewById(R.id.tvDniNoSocio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_no_socio, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.tvNombre.text = "${item.nombre} ${item.apellido}"
        holder.tvDni.text = item.dni

        holder.itemView.setOnClickListener { onClick(item) }
    }
}
