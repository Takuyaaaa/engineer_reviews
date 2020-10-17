package com.engineer_reviews.book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDB
import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.models.book.Book
import com.engineer_reviews.models.book.BookRepository
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import kotlin.test.assertEquals

class BookRepositoryTest {
    @Test
    fun testGetAll() {
        InitDBForTest()
        // create Books Table
        transaction {
            SchemaUtils.drop(Books)
            SchemaUtils.create(Books)
        }

        // save entity
        BookRepository.save(Book(
                "test",
                1000,
                1,
                5.5,
                "https://this/is/test"
        ))

        // --------------------------------------

        // get list of books from db
        val bookList = BookRepository.getAll()

        // --------------------------------------

        // operation should be done as expected
        assertEquals(1, bookList.count())
    }

    @Test
    fun testFind() {
        InitDBForTest()
        // create Books Table
        transaction {
            SchemaUtils.drop(Books)
            SchemaUtils.create(Books)
        }

        // save entity
        val book = BookRepository.save(Book(
            "test",
            1000,
            1,
            5.5,
            "https://this/is/test"
        ))

        // --------------------------------------

        // find the book created by its id
        val targetBook = BookRepository.find(book.id)

        // --------------------------------------

        // operation should be done as expected
        // todo: implement "isEqual" method
        assertEquals(book.id, targetBook.id)
    }

}