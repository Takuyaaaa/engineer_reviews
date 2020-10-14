package com.engineer_reviews.models.book

class Book(
        var title: String,
        var price: Int,
        var category: Int,
        var reviewScore: Int,
        var url: String,
        val id: Int? = null)