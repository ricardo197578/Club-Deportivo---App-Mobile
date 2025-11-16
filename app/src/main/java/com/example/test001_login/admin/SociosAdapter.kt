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
    private val onClick: (Socio) -> Unit     // callback para editar
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
        val socio = items[position]

        holder.tvNombre.text = socio.nombre
        holder.tvDni.text = socio.dni
        
        //El RecyclerView muestra la lista usando SociosAdapter
        //El adapter convierte cada socio en un item visual.
        // Muestra el estado según su campo "activo".
        holder.tvEstado.text = when (socio.activo) {
            1 -> "Activo"
            0 -> "Inactivo"
            2 -> "Vencido"
            else -> "Desconocido"
        }

        holder.card.setOnClickListener {
            onClick(socio)  //  Devuelve el socio completo al Activity,Cuando se hace clic, el adaptador envía el socio a la activity siguiente (SocioFormActivity).
        }
    }

    override fun getItemCount(): Int = items.size
}
