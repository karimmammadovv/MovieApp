package com.karimmammadov.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karimmammadov.movieapp.R
import com.karimmammadov.movieapp.model.Movie
import kotlinx.android.synthetic.main.movie_items.view.*

class MovieAdapter(
    private val  movies: List<Movie>
):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private  val  IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: Movie){
            itemView.movieName.text = movie.title
           itemView.movieRelease.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE+ movie.poster).into(itemView.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.movie_items,parent,false)
       )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       holder.bindMovie(movies.get(position))
    }

    override fun getItemCount(): Int = movies.size
}

