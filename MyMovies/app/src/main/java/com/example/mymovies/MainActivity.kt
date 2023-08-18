package com.example.mymovies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope

import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.model.Movie
import com.example.mymovies.model.MovieDbClient
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        hideNavegationBarBottom()
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
       }




    }

    private fun hideNavegationBarBottom() {
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    private fun navigateTo(it: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE,it)
        startActivity(intent)
    }
}

