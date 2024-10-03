package com.example.seagradece2

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.seagradece2.R

class SongListAdapter(private val context: Activity, private val songs: List<Song>) :
    ArrayAdapter<Song>(context, R.layout.song_item, songs) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.song_item, null, true)

        val titleText = rowView.findViewById<TextView>(R.id.songNameTextView)
        val imageView = rowView.findViewById<ImageView>(R.id.songImageView)
        val durationText = rowView.findViewById<TextView>(R.id.songDurationTextView)

        titleText.text = songs[position].name
        imageView.setImageResource(songs[position].imageResId)
        durationText.text = songs[position].duration

        return rowView
    }
}