package com.example.doglist

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doglist.RecyclerView.DogAdapter
import com.example.doglist.databinding.ActivityMainBinding
import com.example.doglist.ui.theme.DogListTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.zip.Inflater

class MainActivity : ComponentActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter // cramos un adapter
    private val dogImages = mutableListOf<String>() // lista de images, inicial vacio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.svDogs.setOnQueryTextListener(this)
        //initRecyclerView()

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .getDogsByBreeds("$query/images")

            val puppies = call.body() // contiene nuestro DogsResponse
            //vuelve al hilo principal para modificar la parte visual

            runOnUiThread {
                if(call.isSuccessful){
                    //show recyclerview
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear() //borramos lo que contenga el recyclerView
                    dogImages.addAll(images) //añadimos las imagenes
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    //funcion para inicializar el recyclerView
    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase().trim())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

}



