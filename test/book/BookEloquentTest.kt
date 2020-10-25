package book

import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.model.book.BookRepository
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class BookEloquentTest {

    @Test
    fun testToEntity() {
        // save entity
        val book = BookRepository.save(BookRepositoryTest.entity())
        // prepare eloquent
        val eloquent = BookRepository.getAll()[0]
        // --------------------------------------

        // convert eloquent to entity
        val convertedBook = eloquent.toEntity()

        // --------------------------------------

        // operation should be done as expected
        assertEquals(book.id?.value, convertedBook.id?.value)
    }

    // --------------------------------------

    @BeforeTest
    fun initDB() {
        InitDBForTest()
    }
}