/**
 * Descripción: Fragment para la selección de canciones
 * Autor: Assistant
 * Fecha creación: 24/09/2024
 * Fecha última modificación: 24/09/2024
 */
// Descripción: define el fragmento SongSelectionFragment, que muestra una lista
// de canciones utilizando un ListView y un adaptador personalizado. Cada canción
// tiene un nombre, una imagen, una duración y un recurso de audio. Al seleccionar
// una canción, el fragmento llama a MainActivity para mostrar el fragmento de
// reproducción con la canción seleccionada.
// Autor: Fabricio Gabriel Carrazco Arana
// Fecha de creación: 30/09/2024
// Última modificación: 02/10/2024
package com.example.seagradece2

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.seagradece2.R

class SongSelectionFragment : Fragment() {
    private lateinit var listView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_song_selection, container, false)

        listView = view.findViewById(R.id.songListView)

        val songs = listOf(
            Song("Reggaeton", R.drawable.reggaeton_icon, "3:45", R.raw.reague_song),
            Song("Balada", R.drawable.balada_icon, "4:30", R.raw.ballad),
            Song("Rock Clásico", R.drawable.rock_icon, "5:15", R.raw.classic_rock),
            Song("Cumbia", R.drawable.cumbia_icon, "3:20", R.raw.cumbia_classic),
            Song("Electrónica", R.drawable.electronica_icon, "4:00", R.raw.electronic)
        )

        val adapter = SongListAdapter(activity, songs)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            (activity as? MainActivity)?.showPlayerFragment(songs[position])
        }

        return view
    }
}