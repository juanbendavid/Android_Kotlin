package com.example.reciclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reciclerview.R
import com.example.reciclerview.SuperHero

//clase que se encarga basicamente de insertar cada item al recyclerView
class SuperHeroAdapter(private val superHeroList:List<SuperHero>) : RecyclerView.Adapter<SuperHeroViewHolder>(){

    //aqui le pasamos el item de la lista para pintarlo con el layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {

        //si no sabemos el contexto, usar el view que se utiliza en la clase
        val layoutInflater  = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    //este metodo pasa por cada item y llama a la funcion render()
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item)
    }

    //devuelve la cantidad de items dentro de la lista
    override fun getItemCount(): Int =  superHeroList.size



}