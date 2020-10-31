package com.engineer_reviews.model.book.value_objects

class BookUrl(val value: String) {

    init {
        if (value.length > 510) {
            throw Exception("VALIDATE FAILED")
        }

        if (!"http(s)?:\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- .\\/?%&=]*)?"
                .toRegex().containsMatchIn(value)
        ) {
            throw Exception("VALIDATE FAILED")
        }
    }
}