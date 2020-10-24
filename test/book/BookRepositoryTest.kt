package com.engineer_reviews.book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.models.book.Book
import com.engineer_reviews.models.book.BookRepository
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class BookRepositoryTest {
    @Test
    fun testGetAllAndSave() {
        // save entity
        BookRepository.save(entity())

        // --------------------------------------

        // get list of books from db
        val bookList = BookRepository.getAll()

        // --------------------------------------

        // operation should be done as expected
        assertEquals(1, bookList.count())
    }

    @Test
    fun testFind() {
        // save entity
        val book = BookRepository.save(entity())

        // --------------------------------------

        // find the book created by its id
        val targetBook = BookRepository.find(book.id)

        // --------------------------------------

        // operation should be done as expected
        targetBook?.let { assertTrue(book.isEqual(targetBook)) }
    }

    @Test
    fun testUpdate() {
        // save entity
        val book = BookRepository.save(entity())

        // --------------------------------------

        // update entity
        val updatedBook = BookRepository.update(book.id, entity2())

        // --------------------------------------

        // operation should be done as expected
        assertEquals(entity2().title, updatedBook.title)
        assertEquals(entity2().price, updatedBook.price)
        assertEquals(entity2().category, updatedBook.category)
        assertEquals(entity2().reviewScore, updatedBook.reviewScore)
        assertEquals(entity2().url, updatedBook.url)
    }

    @Test
    fun testDelete() {
        // save entity
        val book = BookRepository.save(entity())
        println(entity2().id)

        // --------------------------------------

        // delete entity
        BookRepository.delete(book.id)

        // --------------------------------------

        // operation should be done as expected
        assertFails {
            BookRepository.find(book.id)
        }
    }

    // --------------------------------------

    companion object {
        fun entity(): Book {
            return Book(
                "test",
                1000,
                1,
                5.5,
                "https://this/is/test"
            )
        }

        fun entity2(): Book {
            return Book(
                "test2",
                2000,
                2,
                10.5,
                "https://this/is/test/2"
            )
        }
    }

    // --------------------------------------

    @BeforeTest
    fun initDB() {
        InitDBForTest()
        // create Books Table
        transaction {
            SchemaUtils.drop(Books)
            SchemaUtils.create(Books)
        }
    }
}