package com.engineer_reviews.model.book.value_objects

class BookImagePath(val value: String) {

    init {
        if (value.length > 510) {
            throw Exception("VALIDATE FAILED")
        }
    }
}