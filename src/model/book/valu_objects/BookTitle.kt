package com.engineer_reviews.model.book.valu_objects

class BookTitle(val value: String) {

    init {
        if (value.length > 255) {
            throw Exception("VALIDATE FAILED")
        }
    }
}