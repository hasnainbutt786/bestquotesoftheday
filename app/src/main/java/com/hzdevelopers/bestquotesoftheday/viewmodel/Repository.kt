package com.hzdevelopers.bestquotesoftheday.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.hzdevelopers.bestquotesoftheday.apiInterfaces.QuotesCategoryApi
import com.hzdevelopers.bestquotesoftheday.models.QuoteCategoryModel
import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Repository(private val context : Context) {
    private val cacheSize = (10 * 1024 * 1024).toLong()
    private val cache = Cache(context.cacheDir, cacheSize)

    private val okHttpClient = OkHttpClient.Builder().cache(cache).build()

    private val retrofit = Retrofit.Builder().baseUrl("https://hasnainbutt786.github.io/localhost/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService = retrofit.create(QuotesCategoryApi::class.java)

    fun getDirctoryData() : LiveData<Result<QuoteCategoryModel>>{
        val liveData = MutableLiveData<Result<QuoteCategoryModel>>()

        val call = apiService.getAllCategory()

        call.enqueue(object : Callback<QuoteCategoryModel>{
            override fun onResponse(
                call: Call<QuoteCategoryModel>,
                response: Response<QuoteCategoryModel>
            ) {
                if (response.isSuccessful){
                    val directory = response.body()
                    directory?.let {
                        val result : Result<QuoteCategoryModel> = Result.Success(it)
                        liveData.value = result
                        saveDirectoryToCache(it)
                    }
                }else {
                    liveData.value = Result.Error("Request failed with code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<QuoteCategoryModel>, t: Throwable) {
                liveData.value = Result.Error(t.message ?: "Request failed")
            }

        })
        return liveData
    }

    fun getViewAllData() : LiveData<Result<ViewAllCategoriesModel>>{
        val liveData = MutableLiveData<Result<ViewAllCategoriesModel>>()

        val call = apiService.getAllQuotes()

        call.enqueue(object : Callback<ViewAllCategoriesModel>{
            override fun onResponse(
                call: Call<ViewAllCategoriesModel>,
                response: Response<ViewAllCategoriesModel>
            ) {
                if (response.isSuccessful){
                    val directory = response.body()
                    directory?.let {
                        val result : Result<ViewAllCategoriesModel> = Result.Success(it)
                        liveData.value = result
                        saveDirectoryToCache(it)
                    }
                }else {
                    liveData.value = Result.Error("Request failed with code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ViewAllCategoriesModel>, t: Throwable) {
                liveData.value = Result.Error(t.message ?: "Request failed")
            }

        })
        return liveData
    }

    private fun saveDirectoryToCache(directory: QuoteCategoryModel) {
        val json = Gson().toJson(directory)
        val cacheFile = File(context.cacheDir, "directory.cache")
        cacheFile.writeText(json)
    }

    private fun saveDirectoryToCache(directory: ViewAllCategoriesModel) {
        val json = Gson().toJson(directory)
        val cacheFile = File(context.cacheDir, "quotes.cache")
        cacheFile.writeText(json)
    }

    fun getQuotesFromCache(): Result<ViewAllCategoriesModel> {
        val cacheFile = File(context.cacheDir, "quotes.cache")
        if (cacheFile.exists()) {
            val json = cacheFile.readText()
            val directory = Gson().fromJson(json, ViewAllCategoriesModel::class.java)
            return Result.Success(directory)
        }
        return Result.Error("No cached data available")
    }

    fun getDirectoryFromCache(): Result<QuoteCategoryModel> {
        val cacheFile = File(context.cacheDir, "directory.cache")
        if (cacheFile.exists()) {
            val json = cacheFile.readText()
            val directory = Gson().fromJson(json, QuoteCategoryModel::class.java)
            return Result.Success(directory)
        }
        return Result.Error("No cached data available")
    }
}