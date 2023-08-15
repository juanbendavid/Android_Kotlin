package com.example.doglist.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)

    //función bind() que se llamará desde el adapter y le pasará una url en formato String
    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivDog)
    }
}