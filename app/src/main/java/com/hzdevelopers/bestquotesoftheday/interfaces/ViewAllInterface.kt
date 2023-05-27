package com.hzdevelopers.bestquotesoftheday.interfaces

import com.hzdevelopers.bestquotesoftheday.models.ViewAllCategoriesModel

interface ViewAllInterface {
    fun onClick(model: ViewAllCategoriesModel,name:String,position:Int)
}