package book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository
import com.engineer_reviews.model.book.valu_objects.*
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
        BookRepository.save(BookTest.entity())

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
        val book = BookRepository.save(BookTest.entity())

        // --------------------------------------

        // find the book created by its id
        val targetBook = book.id?.let { BookRepository.find(it) }

        // --------------------------------------

        // operation should be done as expected
        targetBook?.let { assertTrue(book.isEqual(targetBook)) }
    }

    @Test
    fun testUpdate() {
        // save entity
        val book = BookRepository.save(BookTest.entity())

        // --------------------------------------

        // update entity
        val updatedBook = book.id?.let { BookRepository.update(it, BookTest.entity2()) }

        // --------------------------------------

        // operation should be done as expected
        if (updatedBook != null) {
            val book2 = BookTest.entity2()
            assertEquals(book2.title.value, updatedBook.title.value)
            assertEquals(book2.price.value, updatedBook.price.value)
            assertEquals(book2.category.value, updatedBook.category.value)
            assertEquals(book2.reviewScore.value, updatedBook.reviewScore.value)
            assertEquals(book2.url.value, updatedBook.url.value)
        }
    }

    @Test
    fun testDelete() {
        // save entity
        val book = BookRepository.save(BookTest.entity())

        // --------------------------------------

        // delete entity
        book.id?.let { BookRepository.delete(it) }

        // --------------------------------------

        // operation should be done as expected
        assertFails {
            book.id?.let { BookRepository.find(it) }
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