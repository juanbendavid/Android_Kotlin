package com.example.mymovies

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.model.MovieDbClient
import kotlin.concurrent.thread


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviesAdapter = MoviesAdapter(emptyList()) {
            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
        }

        binding.recycler.adapter = moviesAdapter

        //hilo secundario
        thread {
            val apiKey=getString(R.string.api_key)
            val pupularMovies = MovieDbClient.service.listPopularMovies(apiKey)
            val body = pupularMovies.execute().body()

            //aqui se ejecuta el hilo principal, para acatualizar los datos de peliculas
            runOnUiThread{
                if(body!=null)
                    //Log.d("ojo", "Movie Count: ${body.results.size}")
                    moviesAdapter.movies = body.results
                    moviesAdapter.notifyDataSetChanged() // indica que se actualiza datos al adapter

            }


        }


    }
}

