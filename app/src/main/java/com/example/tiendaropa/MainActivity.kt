package com.example.tiendaropa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

// cambiar letras y comentar
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val editPrenda = findViewById<EditText>(R.id.textPrenda)
        val editTalla = findViewById<EditText>(R.id.textTalla)
        val editPrecio = findViewById<EditText>(R.id.textPrecio)
        val botonGuardar = findViewById<Button>(R.id.botonGuardar)
        val botonHistorial = findViewById<Button>(R.id.botonHistorial)
        val botonLimpiar = findViewById<Button>(R.id.botonBorrar)

        val prefs = getSharedPreferences("Historial de compra", MODE_PRIVATE)

        editPrenda.setText(prefs.getString("PRENDA_GUARDADA", ""))
        editTalla.setText(prefs.getString("TALLA_GUARDADA", ""))
        editPrecio.setText(prefs.getString("PRECIO_GUARDADO", ""))

        botonGuardar.setOnClickListener {
            val prenda = editPrenda.text.toString().trim() // trim() para que no cuenten los espacios
            val talla = editTalla.text.toString().trim()
            val precio = editPrecio.text.toString().trim()

            when {
                prenda.isEmpty() || talla.isEmpty() || precio.isEmpty() -> {
                    Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                }
                precio.toDoubleOrNull() == null -> {
                    Toast.makeText(this, "El precio no es válido", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    prefs.edit()
                        .putString("PRENDA_GUARDADA", prenda)
                        .putString("TALLA_GUARDADA", talla)
                        .putString("PRECIO_GUARDADO", precio)
                        .apply()

                    // NUEVO: añadir al historial
                    Historial.prendas.add(Prenda(prenda, talla, precio))

                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("PRENDA_EXTRA", prenda)
                    intent.putExtra("TALLA_EXTRA", talla)
                    intent.putExtra("PRECIO_EXTRA", precio)
                    startActivity(intent)
                }
            }
        }

        // NUEVO: botón Ver Historial
        botonHistorial.setOnClickListener {
            startActivity(Intent(this, HistorialActivity::class.java))
        }

        botonLimpiar.setOnClickListener {
            editPrenda.text.clear()
            editTalla.text.clear()
            editPrecio.text.clear()
            prefs.edit().clear().apply()

            // NUEVO: vaciar también el historial
            Historial.prendas.clear()

            Toast.makeText(this, "Datos borrados", Toast.LENGTH_SHORT).show()
        }
    }
}