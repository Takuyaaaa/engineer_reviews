package com.engineer_reviews.models.book

import com.engineer_reviews.database.dao.Books
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.lang.Exception

class BookRepository {

    companion object {
        fun getAll(): List<BookEloquent> {
            var allBooks: List<BookEloquent> = listOf()
            transaction {
                allBooks = BookEloquent.all().toList()
            }

            return allBooks
        }

        fun find(id: Int): Book {
            return transaction {
                BookEloquent.findById(id)?.toEntity() ?: throw Exception("404")
            }
        }

        fun save(book: Book): Book {
            lateinit var createdBook: Book
            transaction {
                createdBook = BookEloquent.new {
                    title = book.title
                    price = book.price
                    category = book.category
                    reviewScore = book.reviewScore
                    url = book.url
                }.toEntity()
            }
            return createdBook
        }

        fun update(id: Int, newBook: Book): Book {
            val targetBook = find(id)
            lateinit var updatedBook: Book
            transaction {
                updatedBook = BookEloquent.find { Books.id eq targetBook.id}.single().apply {
                    title = newBook.title
                    price = newBook.price
                    category = newBook.category
                    reviewScore = newBook.reviewScore
                    url = newBook.url
                }.toEntity()
            }
            return updatedBook
        }

        fun delete(id: Int): Book {
            val targetBook = find(id)
            transaction {
                Books.deleteWhere { Books.id eq targetBook.id }
            }
            return targetBook
        }
    }

}