package com.engineer_reviews.model.book.valu_objects

class BookReviewScore(val value: Double) {

    init {
        if (value < 0 || value > 10) {
            throw Exception("VALIDATE FAILED")
        }
    }
}