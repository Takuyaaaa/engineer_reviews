package book.value_objects

import book.BookRepositoryTest
import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.model.book.BookRepository
import com.engineer_reviews.model.book.valu_objects.BookId
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
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
        val book = BookRepository.save(BookRepositoryTest.entity())
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
        // create Books Table
        transaction {
            SchemaUtils.drop(Books)
            SchemaUtils.create(Books)
        }
    }
}