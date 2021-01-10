package com.azuka.themovieapp.presentation.feature.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by ivanaazuka on 10/01/21.
 * Android Engineer
 */

class Adapter(
    private val clickListener: (Movie) -> Unit
) : PagedListAdapter<Movie, Adapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movie, clickListener: (Movie) -> Unit) {
            itemView.apply {
                Picasso.get().load(data.posterPath)
                    .into(ivMovie)
                tvMovieTitle.text = data.title
                tvItemLanguage.text = data.originalLanguage
                ratingBar.rating = (data.voteAverage / 2).toFloat()
                tvItemRating.text = context.getString(R.string.item_votes, data.voteAverage)
                itemMovie.setOnClickListener { clickListener.invoke(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(movie, clickListener)
        }
    }
}