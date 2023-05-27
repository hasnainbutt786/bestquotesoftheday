package com.hzdevelopers.bestquotesoftheday.models

data class QuoteCategoryModel(
    val quotesCategories : List<QuotesCategory>
)

data class QuotesCategory(
    val avatar: String,
    val name: String,
    val totalQuotes: String
)