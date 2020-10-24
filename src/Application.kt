package com.engineer_reviews

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDB
import com.engineer_reviews.model.book.Book
import com.engineer_reviews.models.book.BookRepository
import com.engineer_reviews.route.roots
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.transaction

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
        roots()
    }
}