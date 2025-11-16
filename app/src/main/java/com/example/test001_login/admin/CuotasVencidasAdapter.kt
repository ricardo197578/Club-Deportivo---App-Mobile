package com.example.test001_login.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test001_login.R

class CuotasVencidasAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<CuotasVencidasAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvInfo: TextView = v.findViewById(R.id.tvCuotaVencida)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cuota_vencida, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tvInfo.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}
