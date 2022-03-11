package com.karimmammadov.movieapp.services

import com.karimmammadov.movieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieAPI {
    @GET("/3/movie/popular?api_key=f145beef17627d1e516850eb2ab896dd")
    fun getMovieList() : Call<MovieResponse>

}