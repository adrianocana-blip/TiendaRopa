package com.example.tiendaropa

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity2 : ComponentActivity() {

    private var reproductor: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textoPrenda = findViewById<TextView>(R.id.textoPrenda)
        val textoTalla = findViewById<TextView>(R.id.textoTalla)
        val textoPrecio = findViewById<TextView>(R.id.textoPrecio)
        val imagenPrenda = findViewById<ImageView>(R.id.imagenPrenda)
        val botonVolver = findViewById<Button>(R.id.botonVolver)

        val prenda = intent.getStringExtra("PRENDA_EXTRA") ?: ""
        val talla = intent.getStringExtra("TALLA_EXTRA") ?: ""
        val precio = intent.getStringExtra("PRECIO_EXTRA") ?: ""

        textoPrenda.text = "Has pedido: $prenda"
        textoTalla.text = "Talla: $talla"
        textoPrecio.text = "Precio: $precio €"

        val texto = prenda.lowercase()
        val sonidoId: Int

        when {
            texto.contains("camisa") || texto.contains("camiseta") -> {
                imagenPrenda.setImageResource(R.drawable.camisa)
                sonidoId = R.raw.sonido_camisa
            }
            texto.contains("pantalon") || texto.contains("pantalón") || texto.contains("vaquero") -> {
                imagenPrenda.setImageResource(R.drawable.pantalon)
                sonidoId = R.raw.sonido_pantalon
            }
            texto.contains("zapato") || texto.contains("zapatilla") -> {
                imagenPrenda.setImageResource(R.drawable.zapatos)
                sonidoId = R.raw.sonido_zapatos
            }
            else -> {
                imagenPrenda.setImageResource(R.drawable.generica)
                sonidoId = R.raw.sonido_generico
            }
        }

        reproductor = MediaPlayer.create(this, sonidoId)
        reproductor?.start()

        botonVolver.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        reproductor?.release()
        reproductor = null
    }
}