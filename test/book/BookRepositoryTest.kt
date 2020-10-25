package book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository
import com.engineer_reviews.model.book.valu_objects.BookCategory
import com.engineer_reviews.model.book.valu_objects.BookPrice
import com.engineer_reviews.model.book.valu_objects.BookReviewScore
import com.engineer_reviews.model.book.valu_objects.BookTitle
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
        val targetBook = book.id?.let { BookRepository.find(it) }

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
        val updatedBook = book.id?.let { BookRepository.update(it, entity2()) }

        // --------------------------------------

        // operation should be done as expected
        if (updatedBook != null) {
            assertEquals(entity2().title.value, updatedBook.title.value)
            assertEquals(entity2().price.value, updatedBook.price.value)
            assertEquals(entity2().category.value, updatedBook.category.value)
            assertEquals(entity2().reviewScore.value, updatedBook.reviewScore.value)
            assertEquals(entity2().url, updatedBook.url)
        }
    }

    @Test
    fun testDelete() {
        // save entity
        val book = BookRepository.save(entity())

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

    companion object {
        fun entity(): Book {
            return Book(
                BookTitle("test"),
                BookPrice(1000),
                BookCategory.SERVER_SIDE(),
                BookReviewScore(5.5),
                "https://this/is/test"
            )
        }

        fun entity2(): Book {
            return Book(
                BookTitle("test2"),
                BookPrice(2000),
                BookCategory.FRONT_SIDE(),
                BookReviewScore(7.0),
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