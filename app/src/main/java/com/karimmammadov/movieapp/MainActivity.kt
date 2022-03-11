package com.karimmammadov.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.karimmammadov.movieapp.adapter.MovieAdapter
import com.karimmammadov.movieapp.model.Movie
import com.karimmammadov.movieapp.model.MovieResponse
import com.karimmammadov.movieapp.services.MovieAPI
import com.karimmammadov.movieapp.services.MovieAPIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
    }
    private lateinit var moviesList : ArrayList<Movie>
    private lateinit var moviesListNew : ArrayList<Movie>
    private lateinit var movieAdapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesList = ArrayList<Movie>()
        moviesListNew = ArrayList<Movie>()

        rvMovieList.layoutManager = LinearLayoutManager(this)
        rvMovieList.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            moviesList.addAll(movies)
            moviesListNew.addAll(moviesList)
            movieAdapter = MovieAdapter(moviesListNew)
            rvMovieList.adapter = movieAdapter
        }
    }

    private  fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieAPIService
            .getInstance()
            .create(MovieAPI :: class.java)

        apiService.getMovieList()
            .enqueue(object : retrofit2.Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure", t)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id  = item.itemId
        if(id == R.id.sort1){
            println("1")
            moviesListNew.clear()
            moviesListNew.addAll(moviesList.sortedBy { it.title })
            println(moviesListNew.get(0).title)
            movieAdapter.notifyDataSetChanged()
        }else if (id == R.id.sort2){
            println("2")
            moviesListNew.clear()
            moviesListNew.addAll(moviesList.sortedByDescending { it.title })
            println(moviesListNew.get(0).title)
            movieAdapter.notifyDataSetChanged()
        }else if(id == R.id.sort3){
            println("3")
            moviesListNew.clear()
            moviesListNew.addAll(moviesList.sortedByDescending{ it.release })
            println(moviesListNew.get(0).release)
            movieAdapter.notifyDataSetChanged()
        }else if(id == R.id.sort4){
            println("4")
            moviesListNew.clear()
            moviesListNew.addAll(moviesList.sortedBy { it.release })
            println(moviesListNew.get(0).release)
            movieAdapter.notifyDataSetChanged()
        }
        return super.onOptionsItemSelected(item)
    }
}
