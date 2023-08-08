package com.example.reciclerview.adapter

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.reciclerview.R
import com.example.reciclerview.SuperHero
import org.w3c.dom.Text

class SuperHeroViewHolder(view:View) : ViewHolder(view){

    val superHero = view.findViewById<TextView>(R.id.tvSuperHeroName)
    val realName = view.findViewById<TextView>(R.id.tvRealName)
    val publisher = view.findViewById<TextView>(R.id.tvpublisher)
    val photo = view.findViewById<ImageView>(R.id.ivSuperHero)

    //funcion que recibe un objeto SuperHero, la funcion se llama por cada item superHeroe
    //modifica los textView
    fun render(superHeroModel:SuperHero){
        superHero.text = superHeroModel.superHero
        realName.text = superHeroModel.realName
        publisher.text = superHeroModel.publisher
    }
}