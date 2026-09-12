package com.sofia.pokeapp

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Encontrar las vistas del layout
        val listView = findViewById<ListView>(R.id.listViewPokemon)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // 2. Lanzar una corutina ligada al ciclo de vida de la Activity
        lifecycleScope.launch {
            try {
                // 3. Llamar a la API (esto es lo que hace la magia)
                val response = RetrofitClient.api.getPokemonList(limit = 20)

                // 4. Sacar solo los nombres y capitalizarlos
                val nombres = response.results.map { pokemon ->
                    pokemon.name.replaceFirstChar { it.uppercase() }
                }

                // 5. Crear el adaptador con un layout estándar de Android
                val adapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_list_item_1,
                    nombres
                )

                // 6. Asignarle el adaptador al ListView y ocultar el spinner
                listView.adapter = adapter
                progressBar.visibility = View.GONE

            } catch (e: Exception) {
                // Si algo falla (sin internet, server caído, etc.)
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${e.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}