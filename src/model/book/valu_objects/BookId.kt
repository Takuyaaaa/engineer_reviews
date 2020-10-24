package com.engineer_reviews.model.book.valu_objects

import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository
import kotlin.properties.Delegates

class BookId(val id: Int) {

    var value by Delegates.notNull<Int>()

    init {
        value = id
    }

    fun toEntity(): Book {
        return BookRepository.find(this) ?: throw Exception("404")
    }
}