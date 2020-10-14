package com.engineer_reviews

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
import java.lang.Exception

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

        BookRepository.save(Book("Test Title", 2000, 1, 5, "https://this/is/sample"))
        BookRepository.save(Book("Test Title2", 3000, 2, 8, "https://this/is/sample/2"))
    }

    routing {
        route("/book") {
            get {
                call.respond(BookRepository.getAll().map { it.toEntity() })
            }
            post {
                val newBook = call.receive<Book>()
                BookRepository.save(newBook)
                call.respond(newBook)
            }
            route("{id}") {
                get {
                    val id = call.parameters["id"]?.toInt()
                    val targetBook = BookRepository.find(id)?.toEntity() ?: throw Exception("404")
                    call.respond(targetBook)
                }
                put {
                    val newBook = call.receive<Book>()
                    val id = call.parameters["id"]?.toInt()
                    val targetBook = BookRepository.find(id)?.toEntity() ?: throw Exception("404")
                    val updatedBook = BookRepository.update(targetBook, newBook)
                    call.respond(updatedBook)
                }
                delete {
                    val id = call.parameters["id"]?.toInt()
                    val targetBook = BookRepository.find(id)?.toEntity() ?: throw Exception("404")
                    val deletedBook = BookRepository.delete(targetBook)
                    call.respond(deletedBook)
                }
            }
        }
    }
}

