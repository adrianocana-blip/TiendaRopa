package com.example.tiendaropa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PrendaAdapter(
    private val lista: List<Prenda>,
    private val onLongClickBorrar: (Prenda) -> Unit
) : RecyclerView.Adapter<PrendaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvItemPrenda)
        val tvTalla: TextView = view.findViewById(R.id.tvItemTalla)
        val tvPrecio: TextView = view.findViewById(R.id.tvItemPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prenda, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prenda = lista[position]
        holder.tvNombre.text = prenda.nombre
        holder.tvTalla.text = "Talla: ${prenda.talla}"
        holder.tvPrecio.text = "${prenda.precio} €"

        // Reto de borrado con pulsación larga sobre el elemento
        holder.itemView.setOnLongClickListener {
            onLongClickBorrar(prenda)
            true
        }
    }

    override fun getItemCount(): Int = lista.size
}