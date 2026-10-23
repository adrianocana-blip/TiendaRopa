package com.example.tiendaropa

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistorialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val rv = findViewById<RecyclerView>(R.id.recyclerHistorial)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = PrendaAdapter(Historial.prendas)

        findViewById<Button>(R.id.botonVolverHistorial).setOnClickListener {
            finish()
        }
    }
}