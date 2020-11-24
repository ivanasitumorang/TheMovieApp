package com.azuka.themovieapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by ivanaazuka on 24/11/20.
 * Android Engineer
 */

abstract class GeneralMovieAdapter<T>(
    private val list: List<T>,
    private val clickListener: (T) -> Unit
) : RecyclerView.Adapter<GeneralMovieAdapter.ViewHolder<T>>() {

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int = list.size

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: T, clickListener: (T) -> Unit)
    }

}

