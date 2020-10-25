package com.engineer_reviews.model.book.valu_objects

import java.lang.Exception
import kotlin.properties.Delegates

class BookReviewScore(val reviewScore: Double) {

    var value by Delegates.notNull<Double>()

    init {
        if (reviewScore < 0 || reviewScore > 10) {
            throw Exception("VALIDATE FAILED")
        }

        value = reviewScore
    }
}