package com.engineer_reviews.model.book.valu_objects

import java.lang.Exception
import kotlin.properties.Delegates

class BookUrl(val url: String) {

    var value by Delegates.notNull<String>()

    init {
        if (url.length > 255) {
            throw Exception("VALIDATE FAILED")
        }

        // todo: url form validation
//        if (!Regex(pattern = "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)\n")
//                .containsMatchIn(url)) {
//            throw Exception("VALIDATE FAILED")
//        }

        value = url
    }
}