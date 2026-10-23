package com.example.tiendaropa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PrendaAdapter(private val lista: List<Prenda>) :
    RecyclerView.Adapter<PrendaAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val prenda: TextView = v.findViewById(R.id.tvItemPrenda)
        val talla: TextView = v.findViewById(R.id.tvItemTalla)
        val precio: TextView = v.findViewById(R.id.tvItemPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prenda, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = lista[position]
        holder.prenda.text = p.nombre
        holder.talla.text = "Talla: ${p.talla}"
        holder.precio.text = "Precio: ${p.precio} €"
    }

    override fun getItemCount() = lista.size
}