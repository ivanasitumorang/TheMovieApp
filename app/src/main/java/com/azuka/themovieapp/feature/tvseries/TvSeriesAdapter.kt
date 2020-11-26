package com.azuka.themovieapp.feature.tvseries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azuka.themovieapp.BuildConfig
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.GeneralMovieAdapter
import com.azuka.themovieapp.data.TvSeries
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

class TvSeriesAdapter(
    list: List<TvSeries>,
    clickListener: (TvSeries) -> Unit
) : GeneralMovieAdapter<TvSeries, TvSeriesAdapter.TvSeriesViewHolder>(list, clickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<TvSeries> {
        return TvSeriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    inner class TvSeriesViewHolder(itemView: View) :
        GeneralMovieAdapter.ViewHolder<TvSeries>(itemView) {
        override fun bind(data: TvSeries, clickListener: (TvSeries) -> Unit) {
            itemView.apply {
                val posterPath = "${BuildConfig.TMDB_IMAGE_URL}/w500/${data.posterPath}"
                Picasso.get().load(posterPath)
                    .into(ivMovie)
                tvMovieTitle.text = data.name
                tvItemLanguage.text = data.originalLanguage
                ratingBar.rating = (data.voteAverage / 2).toFloat()
                tvItemRating.text = "(${data.voteAverage} of 10)"
                itemMovie.setOnClickListener { clickListener.invoke(data) }
            }
        }

    }
}