package book.value_objects

import book.BookTest
import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.model.book.BookRepository
import com.engineer_reviews.model.book.valu_objects.BookId
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFails

class BookIdTest {
    @Test
    fun testValidation() {
        // id must not be negative
        assertFails {
            BookId(-1)
        }

        // id must be positive
        BookId(1)
    }

    @Test
    fun testToEntity() {
        // save entity
        val book = BookRepository.save(BookTest.entity())
        val bookId = book.id

        // --------------------------------------

        // convert id to entity
        val convertedBook = bookId?.toEntity()

        // --------------------------------------

        // operation should be done as expected
        assertEquals(book.id?.value, convertedBook?.id?.value)
    }

    // --------------------------------------

    @BeforeTest
    fun initDB() {
        InitDBForTest()
    }
}