/**
 * Descripción: Actividad principal que maneja los Fragments del reproductor de música
 * Autor: Assistant
 * Fecha creación: 24/09/2024
 * Fecha última modificación: 24/09/2024
 */
// Descripción: Esta clase MainActivity extiende
// de Activity y administra dos fragmentos: uno para la selección de canciones
// y otro para reproducir la canción seleccionada.
// Autor: Fabricio Gabriel Carrazco Arana
// Fecha de creación: 30/09/2024
// Última modificación: 02/10/2024
package com.example.seagradece2

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showSongSelectionFragment()
        }
    }

    private fun showSongSelectionFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SongSelectionFragment())
            .commit()
    }

    fun showPlayerFragment(song: Song) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment.newInstance(song))
            .addToBackStack(null)
            .commit()
    }
}
