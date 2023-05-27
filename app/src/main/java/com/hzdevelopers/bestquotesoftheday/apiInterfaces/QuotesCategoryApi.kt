package com.hzdevelopers.bestquotesoftheday.apiInterfaces

import com.hzdevelopers.bestquotesoftheday.models.QuoteCategoryModel
import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface QuotesCategoryApi {

    @GET("quotesCategories")
    fun getAllCategory(): Call<QuoteCategoryModel>

    @GET("allQuotes")
    fun getAllQuotes(): Call<ViewAllCategoriesModel>
}