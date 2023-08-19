package com.example.mvvm.domain

import QuoteModel
import com.example.mvvm.data.model.QuoteRepository

class GetQuotesUseCase {
    private val repository = QuoteRepository()
    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()

}