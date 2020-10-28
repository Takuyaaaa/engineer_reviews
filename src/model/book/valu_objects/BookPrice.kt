package com.engineer_reviews.model.book.valu_objects

import com.fasterxml.jackson.annotation.JsonIgnore

class BookPrice(val value: Int) {

    init {
        if (value < 0) {
            throw Exception("VALIDATE FAILED")
        }
    }
}