package com.engineer_reviews.model.book.valu_objects

import java.lang.Exception
import kotlin.properties.Delegates

class BookPrice(val price: Int) {

    var value by Delegates.notNull<Int>()

    init {
        if (price < 0) {
            throw Exception("VALIDATE FAILED")
        }

        value = price
    }
}