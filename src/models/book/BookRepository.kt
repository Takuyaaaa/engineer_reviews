package com.engineer_reviews.models.book

import com.engineer_reviews.database.dao.Books
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
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

        fun find(id: Int?): BookEloquent? {
            return id?.let { transaction {
                BookEloquent.findById(id)
            }}
        }

        fun save(book: Book) {
            transaction {
                BookEloquent.new {
                    title = book.title
                    price = book.price
                    category = book.category
                    reviewScore = book.reviewScore
                    url = book.url
                }
            }
        }

        fun update(targetBook: Book, newInfo: Book): Book {
            var updatedBook: Book = newInfo
            transaction {
                updatedBook = BookEloquent.find { Books.id eq targetBook.id}.single().apply {
                    title = newInfo.title
                    price = newInfo.price
                    category = newInfo.category
                    reviewScore = newInfo.reviewScore
                    url = newInfo.url
                }.toEntity()
            }
            return updatedBook
        }

        fun delete(targetBook: Book): Book {
            transaction {
                Books.deleteWhere { Books.id eq targetBook.id }
            }
            return targetBook
        }
    }

}