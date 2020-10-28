package com.engineer_reviews.route

import com.engineer_reviews.controller.BookController
import com.engineer_reviews.route.service.extractId
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.roots() {
    val bookController = BookController()

    route("/book") {
        get {
            call.respond(bookController.index())
        }
        post {
            call.respond(bookController.create(call.receive()))
        }
        route("{id}") {
            get {
                bookController.show(extractId(call))?.let { book ->
                    call.respond(book)
                }
            }
            put {
                call.respond(bookController.update(extractId(call), call.receive()))
            }
            delete {
                bookController.delete(extractId(call))?.let { book ->
                    call.respond(book)
                }
            }
        }
    }
}