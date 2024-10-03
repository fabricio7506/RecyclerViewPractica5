// Descripción: define el fragmento PlayerFragment, que se
// encarga de reproducir canciones en una interfaz de usuario
// con botones de control (play, pausa y stop). Se utiliza un MediaPlayer
// para manejar el audio y se cargan el nombre y la imagen de la canción
// seleccionada. Además, el fragmento permite reproducir, pausar y detener
// la canción, y libera el recurso de audio cuando se destruye el fragmento.
// Autor: Fabricio Gabriel Carrazco Arana
// Fecha de creación: 30/09/2024
// Última modificación: 02/10/2024

package com.example.seagradece2

import android.app.Fragment
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.util.Log

class PlayerFragment : Fragment() {
    private lateinit var audioNameText: TextView
    private lateinit var songImageView: ImageView
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private lateinit var mediaPlayer: MediaPlayer
    private var currentSong: Song? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)

        // Referencias a los elementos UI
        audioNameText = view.findViewById(R.id.audioNameText)
        songImageView = view.findViewById(R.id.songImageView)  // Añadimos la referencia al ImageView
        playButton = view.findViewById(R.id.playButton)
        pauseButton = view.findViewById(R.id.pauseButton)
        stopButton = view.findViewById(R.id.stopButton)

        // Obtener el objeto Song del Bundle
        currentSong = arguments?.getSerializable("selectedSong") as Song?

        // Mostrar el nombre de la canción y la imagen
        currentSong?.let { song ->
            audioNameText.text = song.name
            songImageView.setImageResource(song.imageResId)  // Mostrar la imagen correspondiente

            // Aquí mantenemos la lógica de asignación condicional, pero utilizamos el audioResId
            val audioResource = when (song.name) {
                "Reggaeton" -> R.raw.reague_song
                "Balada" -> R.raw.ballad
                "Rock Clásico" -> R.raw.classic_rock
                "Cumbia" -> R.raw.cumbia_classic
                "Electrónica" -> R.raw.electronic
                else -> song.audioResId // Usamos el audioResId en caso de que no coincida con los anteriores xddd me lagrimea el ojo
            }

            try {
                mediaPlayer = MediaPlayer.create(activity, audioResource)
                mediaPlayer.setOnPreparedListener {
                    Log.d("PlayerFragment", "MediaPlayer preparado correctamente")
                }
                mediaPlayer.setOnErrorListener { mp, what, extra ->
                    Log.e("PlayerFragment", "Error en MediaPlayer: what=$what, extra=$extra")
                    true
                }
            } catch (e: Exception) {
                Log.e("PlayerFragment", "Error al crear el MediaPlayer", e)
            }
        }

        // Control de reproducción
        playButton.setOnClickListener {
            try {
                mediaPlayer.start()
            } catch (e: Exception) {
                Log.e("PlayerFragment", "Error al iniciar el MediaPlayer", e)
            }
        }
        pauseButton.setOnClickListener { mediaPlayer.pause() }
        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    companion object {
        // Cambiamos el tipo de parámetro a Song
        fun newInstance(selectedSong: Song): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putSerializable("selectedSong", selectedSong)
            fragment.arguments = args
            return fragment
        }
    }
}
