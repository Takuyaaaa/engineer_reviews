package com.engineer_reviews.model.book.valu_objects

import java.lang.Exception
import kotlin.properties.Delegates

class BookTitle(val title: String) {

    var value by Delegates.notNull<String>()

    init {
        if (title.length > 255) {
            throw Exception("VALIDATE FAILED")
        }

        value = title
    }
}