package com.engineer_reviews.models.book

import com.engineer_reviews.database.dao.Books
import org.jetbrains.exposed.sql.insert
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

        fun save(book: Book) {
            transaction {
                Books.insert {
                    it[title] = book.title
                    it[price] = book.price
                    it[category] = book.category
                    it[reviewScore] = book.reviewScore
                    it[url] = book.url
                }
            }
        }

        fun update(targetBook: Book, newInfo: Book): Book {
            targetBook.title = newInfo.title
            targetBook.price = newInfo.price
            targetBook.category = newInfo.category
            targetBook.reviewScore = newInfo.reviewScore
            targetBook.url = newInfo.url
            // saveだと追加されちゃう
            save(targetBook)
            return targetBook
        }

        fun find(id: Int?): BookEloquent? {
            return id?.let { transaction {
                BookEloquent.findById(it)
            }}
        }
    }

}