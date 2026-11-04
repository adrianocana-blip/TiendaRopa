package com.example.tiendaropa

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HistorialActivity : ComponentActivity() {
    private val dao by lazy { AppDatabase.obtener(this).prendaDao() }
    private lateinit var adapter: PrendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)
        val rv = findViewById<RecyclerView>(R.id.recyclerHistorial)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = PrendaAdapter(emptyList()) { prenda ->
            lifecycleScope.launch {
                dao.borrar(prenda)
                cargarPrendas()
            }
        }
        rv.adapter = adapter
        findViewById<Button>(R.id.botonVolverHistorial).setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        cargarPrendas()
    }

    private fun cargarPrendas() {
        lifecycleScope.launch {
            adapter.actualizarLista(dao.obtenerTodas())
        }
    }
}