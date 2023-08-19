package com.example.mymovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope

import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.model.Movie
import com.example.mymovies.model.MovieDbClient
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressCircular.isVisible = true
        val moviesAdapter = MoviesAdapter(emptyList()) {
            navigateTo(it)
        }

        binding.recycler.adapter = moviesAdapter


        //lifecyclescope utiliza el hilo principal, las peticiones al servidor no se pueden hacer en dicho hilo
       lifecycleScope.launch{
           val apiKey = getString(R.string.api_key)
           val popularMovies = MovieDbClient.service.listPopularMovies(apiKey)

           //Funciones de Suspension, la corrutina se queda suspendida hasta acabar la funcion
           //y el hilo principal no se queda bloqueado, sigue ejecutando lo que necesite
           //fuera el hilo principal, peticion el servidor con IO

           //Log.d("ojo", "Movie Count: ${body.results.size}")
           moviesAdapter.movies = popularMovies.results
           moviesAdapter.notifyDataSetChanged() // indica que se actualiza datos al adapter
           binding.progressCircular.isVisible = false
       }





    }


    private fun navigateTo(it: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE,it)
        startActivity(intent)
    }
}

