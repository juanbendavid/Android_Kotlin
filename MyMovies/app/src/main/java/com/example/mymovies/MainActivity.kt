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

        binding.recycler.adapter = MoviesAdapter(
            listOf(
                Movie(
                    "Title1",
                    "https://media.revistagq.com/photos/64073419d8bd3a1a762bdf58/1:1/w_675,h_675,c_limit/por-que-linterna-verde-fracaso.jpg"
                ),
                Movie("Title2", "url3"),
                Movie("Title3", "url3"),
                Movie("Title4", "url4")

            )
        ) {
            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
        }

        thread {
            val apiKey=getString(R.string.api_key)
            val pupularMovies = MovieDbClient.service.listPopularMovies(apiKey)

            val body = pupularMovies.execute().body()
            if(body!=null)
                Log.d("ojo", "Movie Count: ${body.results.size}")
        }


    }
}

