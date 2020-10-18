package com.engineer_reviews.route.service

import io.ktor.application.*
import kotlin.properties.Delegates

fun extractId(call: ApplicationCall): Int {
    var id by Delegates.notNull<Int>()
    call.parameters["id"]?.toInt()?.let { id = it }
    return id
}