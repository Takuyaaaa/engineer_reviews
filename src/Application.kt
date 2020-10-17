package com.engineer_reviews

import com.engineer_reviews.controllers.BookController
import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDB
import com.engineer_reviews.models.book.Book
import com.engineer_reviews.models.book.BookRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.request.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.properties.Delegates

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    InitDB()

    // create Books Table
    transaction {
        drop(Books)
        SchemaUtils.create(Books)

        BookRepository.save(Book("Test Title", 2000, 1, 5.5, "https://this/is/sample"))
        BookRepository.save(Book("Test Title2", 3000, 2, 8.5, "https://this/is/sample/2"))
    }

    routing {
        val bookController = BookController()

        route("/book") {
            get {
                call.respond(bookController.index())
            }
            post {
                call.respond(bookController.create(call.receive()))
            }
            route("{id}") {
                get{
                    bookController.show(ControllerUtils.extractId(call))?.let { it1 -> call.respond(it1) }

                }
                put {
                    call.respond(bookController.update(ControllerUtils.extractId(call), call.receive()))
                }
                delete {
                    bookController.delete(ControllerUtils.extractId(call))?.let { it1 -> call.respond(it1) }

                }
            }
        }
    }
}

class ControllerUtils {
    companion object {
        fun extractId(call: ApplicationCall): Int {
            var id by Delegates.notNull<Int>()
            call.parameters["id"]?.toInt()?.let { id = it }
            return id
        }
    }
}

