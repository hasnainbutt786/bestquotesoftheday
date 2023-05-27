package com.hzdevelopers.bestquotesoftheday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hzdevelopers.bestquotesoftheday.models.QuoteCategoryModel
import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel

class MyViewModel(private val repository: Repository) : ViewModel() {

    private val _directoryData = MutableLiveData<Result<QuoteCategoryModel>>()
    private val _QuotesData = MutableLiveData<Result<ViewAllCategoriesModel>>()
    val directoryData: LiveData<Result<QuoteCategoryModel>> = _directoryData
    val quotesData: LiveData<Result<ViewAllCategoriesModel>> = _QuotesData

    fun fetchDirectoryData() {
        _directoryData.value = Result.Loading

        repository.getDirctoryData().observeForever { result ->
            _directoryData.value = result
        }
    }

    fun fetchQuotesData(){
        _QuotesData.value = Result.Loading

        repository.getViewAllData().observeForever{result ->
            _QuotesData.value = result
        }
    }

    fun getQuotesFromCache() : Result<ViewAllCategoriesModel>{
        return repository.getQuotesFromCache()
    }
    fun getDirectoryFromCache(): Result<QuoteCategoryModel> {
        return repository.getDirectoryFromCache()
    }
}