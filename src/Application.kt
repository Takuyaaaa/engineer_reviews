package com.engineer_reviews

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDB
import com.engineer_reviews.models.book.Book
import com.engineer_reviews.models.book.BookJson
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import javax.xml.validation.Schema

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

        Books.insert {
            it[title] = "Test Title"
            it[price] = 2000
            it[category] = 1
            it[reviewScore] = 5
            it[bookUrl] = "https://this/is/sample"
        }

        Books.insert {
            it[title] = "Test Title2"
            it[price] = 3000
            it[category] = 2
            it[reviewScore] = 10
            it[bookUrl] = "https://this/is/sample/2"
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/books") {
            var allBooks: List<Book> = listOf()
            transaction {
                allBooks = Book.all().toList()
            }

            call.respond(allBooks.map { BookJson(it.title, it.price, it.category, it.reviewScore, it.bookUrl) })
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

