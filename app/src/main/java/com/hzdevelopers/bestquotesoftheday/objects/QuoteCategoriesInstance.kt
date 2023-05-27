package com.hzdevelopers.bestquotesoftheday.objects


import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuoteCategoriesInstance {

    fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://hasnainbutt786.github.io/localhost/").addConverterFactory(GsonConverterFactory.create()).build()
    }
}