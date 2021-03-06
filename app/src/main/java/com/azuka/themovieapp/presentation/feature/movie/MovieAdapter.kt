package com.azuka.themovieapp.presentation.feature.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.GeneralMovieAdapter
import com.azuka.themovieapp.extension.convertToFiveRatings
import com.azuka.themovieapp.presentation.entity.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class MovieAdapter(
    list: List<Movie>,
    clickListener: (Movie) -> Unit
) : GeneralMovieAdapter<Movie, MovieAdapter.MovieViewHolder>(list, clickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Movie> {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    inner class MovieViewHolder(itemView: View) : GeneralMovieAdapter.ViewHolder<Movie>(itemView) {
        override fun bind(data: Movie, clickListener: (Movie) -> Unit) {
            itemView.apply {
                Picasso.get().load(data.posterPath)
                    .into(ivMovie)
                tvMovieTitle.text = data.title
                tvItemLanguage.text = data.originalLanguage
                ratingBar.rating = data.voteAverage.convertToFiveRatings()
                tvItemRating.text = context.getString(R.string.item_votes, data.voteAverage)
                itemMovie.setOnClickListener { clickListener.invoke(data) }
            }
        }
    }
}

