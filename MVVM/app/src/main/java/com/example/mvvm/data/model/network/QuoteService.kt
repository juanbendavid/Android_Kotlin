package com.example.mvvm.data.model.network

import QuoteModel
import com.example.mvvm.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit() // inicializamos una instancia retrofit

    suspend fun getQuotes(): List<QuoteModel> {

        //ejecuta toda esta llamada en un hilo secundario
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }

}