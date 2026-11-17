package com.example.tiendaropa

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HistorialActivity : AppCompatActivity() {

    private lateinit var dao: PrendaDao
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        dao = AppDatabase.obtener(this).prendaDao()

        // Usamos el ID exacto de tu XML: recyclerHistorial
        recyclerView = findViewById(R.id.recyclerHistorial)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val botonVolver = findViewById<Button>(R.id.botonVolverHistorial)
        botonVolver.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        cargarHistorial()
    }

    private fun cargarHistorial() {
        lifecycleScope.launch {
            val prendasGuardadas = dao.obtenerTodas()
            recyclerView.adapter = PrendaAdapter(prendasGuardadas) { prendaABorrar ->
                borrarDeHistorial(prendaABorrar)
            }
        }
    }

    private fun borrarDeHistorial(prenda: Prenda) {
        lifecycleScope.launch {
            dao.borrar(prenda)
            cargarHistorial()
        }
    }
}