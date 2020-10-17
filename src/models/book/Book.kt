package com.engineer_reviews.models.book

import kotlin.properties.Delegates

class Book(
        var title: String,
        var price: Int,
        var category: Int,
        var reviewScore: Double,
        var url: String) {
        var id by Delegates.notNull<Int>()
}