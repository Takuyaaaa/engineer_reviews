package com.engineer_reviews.model.book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.model.book.valu_objects.BookId
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class BookRepository {

    companion object {
        fun getAll(): List<BookEloquent> {
            var allBooks: List<BookEloquent> = listOf()
            transaction {
                allBooks = BookEloquent.all().toList()
            }

            return allBooks
        }

        fun find(id: BookId): Book? {
            return transaction {
                BookEloquent.findById(id.value)?.toEntity() ?: throw Exception("404")
            }
        }

        fun save(book: Book): Book {
            lateinit var createdBook: Book
            transaction {
                createdBook = BookEloquent.new {
                    title = book.title.value
                    price = book.price.value
                    category = book.category.value
                    reviewScore = book.reviewScore
                    url = book.url
                }.toEntity()
            }
            return createdBook
        }

        fun update(id: BookId, newBook: Book): Book {
            val targetBook = find(id)
            lateinit var updatedBook: Book
            transaction {
                updatedBook = BookEloquent.find { Books.id eq targetBook?.id?.value }.single().apply {
                    title = newBook.title.value
                    price = newBook.price.value
                    category = newBook.category.value
                    reviewScore = newBook.reviewScore
                    url = newBook.url
                }.toEntity()
            }
            return updatedBook
        }

        fun delete(id: BookId): Book? {
            val targetBook = find(id)
            transaction {
                Books.deleteWhere { Books.id eq targetBook?.id?.value }
            }
            return targetBook
        }
    }

}