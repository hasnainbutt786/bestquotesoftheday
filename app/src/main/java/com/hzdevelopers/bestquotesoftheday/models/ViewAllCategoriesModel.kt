package com.hzdevelopers.bestquotesoftheday.models

data class ViewAllCategoriesModel(
    val Authors: List<Author>,
    val Birthday: List<Birthday>,
    val Friends: List<Friend>,
    val Love: List<Love>,
    val Motivations: List<Motivation>,
    val Sad: List<Sad>
)

data class Love(
    val imageUrl: String?
)

data class Sad(
    val imageUrl: String
)

data class Motivation(
    val imageUrl: String
)

data class Friend(
    val imageUrl: String
)

data class Birthday(
    val imageUrl: String
)

data class Author(
    val imageUrl: String
)