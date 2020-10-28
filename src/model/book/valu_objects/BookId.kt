package com.engineer_reviews.model.book.valu_objects

import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository
import com.fasterxml.jackson.annotation.JsonIgnore

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