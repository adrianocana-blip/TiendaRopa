package com.example.tiendaropa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var dao: PrendaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // 1. Obtener la instancia correcta del DAO
        dao = AppDatabase.obtener(this).prendaDao()

        val editPrenda = findViewById<EditText>(R.id.textPrenda)
        val editTalla = findViewById<EditText>(R.id.textTalla)
        val editPrecio = findViewById<EditText>(R.id.textPrecio)
        val botonGuardar = findViewById<Button>(R.id.botonGuardar)
        val botonHistorial = findViewById<Button>(R.id.botonHistorial)
        val botonLimpiar = findViewById<Button>(R.id.botonBorrar)

        botonGuardar.setOnClickListener {
            val prendaTexto = editPrenda.text.toString().trim()
            val tallaTexto = editTalla.text.toString().trim()
            val precioTexto = editPrecio.text.toString().trim().replace(',', '.') // Reemplaza comas por puntos

            val precioDouble = precioTexto.toDoubleOrNull()

            when {
                prendaTexto.isEmpty() || tallaTexto.isEmpty() || precioTexto.isEmpty() -> {
                    Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                }
                precioDouble == null -> {
                    Toast.makeText(this, "El precio no es válido (usa números)", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Creamos el objeto Prenda con los tipos exactos de la Entity
                    val nuevaPrenda = Prenda(
                        nombre = prendaTexto,
                        talla = tallaTexto,
                        precio = precioDouble
                    )

                    // 2. Guardar en Room dentro de la corrutina y cambiar de pantalla al terminar
                    lifecycleScope.launch {
                        dao.insertar(nuevaPrenda)

                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        intent.putExtra("PRENDA_EXTRA", prendaTexto)
                        intent.putExtra("TALLA_EXTRA", tallaTexto)
                        intent.putExtra("PRECIO_EXTRA", precioTexto)
                        startActivity(intent)
                    }
                }
            }
        }

        // Botón Ver Historial
        botonHistorial.setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }

        // Botón Limpiar todo
        botonLimpiar.setOnClickListener {
            editPrenda.text.clear()
            editTalla.text.clear()
            editPrecio.text.clear()
            Toast.makeText(this, "Campos borrados", Toast.LENGTH_SHORT).show()
        }
    }
}