package com.engineer_reviews.model.book.value_objects

import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository

class BookId(val value: Int) {

    init {
        if (value < 0) {
            throw Exception("VALIDATE FAILED")
        }
    }

    fun toEntity(): Book {
        return BookRepository.find(this) ?: throw Exception("404")
    }
}