package com.azuka.themovieapp.presentation.feature.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azuka.themovieapp.R
import com.azuka.themovieapp.presentation.entity.FavoriteGeneral
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by ivanaazuka on 10/01/21.
 * Android Engineer
 */

class FavoriteListAdapter(
    private val clickListener: (FavoriteGeneral) -> Unit
) : PagedListAdapter<FavoriteGeneral, FavoriteListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteGeneral>() {
            override fun areItemsTheSame(
                oldItem: FavoriteGeneral,
                newItem: FavoriteGeneral
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteGeneral,
                newItem: FavoriteGeneral
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: FavoriteGeneral, clickListener: (FavoriteGeneral) -> Unit) {
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