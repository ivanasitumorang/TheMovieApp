package com.azuka.themovieapp.presentation.feature.tvseries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azuka.themovieapp.R
import com.azuka.themovieapp.adapter.GeneralMovieAdapter
import com.azuka.themovieapp.presentation.entity.TvSeries
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
                Picasso.get().load(data.posterPath)
                    .into(ivMovie)
                tvMovieTitle.text = data.name
                tvItemLanguage.text = data.originalLanguage
                ratingBar.rating = (data.voteAverage / 2).toFloat()
                tvItemRating.text = context.getString(R.string.item_votes, data.voteAverage)
                itemMovie.setOnClickListener { clickListener.invoke(data) }
            }
        }

    }
}