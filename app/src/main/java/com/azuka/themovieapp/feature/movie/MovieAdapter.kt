package com.azuka.themovieapp.feature.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azuka.themovieapp.BuildConfig
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.GeneralMovieAdapter
import com.azuka.themovieapp.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieAdapter(
    list: List<Movie>,
    clickListener: (Movie) -> Unit
) : GeneralMovieAdapter<Movie>(list, clickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Movie> {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    inner class MovieViewHolder(itemView: View) : GeneralMovieAdapter.ViewHolder<Movie>(itemView) {
        override fun bind(data: Movie, clickListener: (Movie) -> Unit) {
            itemView.apply {
                val posterPath = "${BuildConfig.TMDB_IMAGE_URL}/w500/${data.posterPath}"
                Picasso.get().load(posterPath)
                    .into(ivMovie)
                tvMovieTitle.text = data.title
                itemMovie.setOnClickListener { clickListener.invoke(data) }
            }
        }
    }

}

