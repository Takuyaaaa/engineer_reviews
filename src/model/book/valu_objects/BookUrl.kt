package com.engineer_reviews.model.book.valu_objects

import kotlin.properties.Delegates

class BookUrl(val url: String) {

    var value by Delegates.notNull<String>()

    init {
        if (url.length > 510) {
            throw Exception("VALIDATE FAILED")
        }

        println(url)
        if (!"http(s)?:\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- .\\/?%&=]*)?"
                .toRegex().containsMatchIn(url)
        ) {
            throw Exception("VALIDATE FAILED")
        }

        value = url
    }
}