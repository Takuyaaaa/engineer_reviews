package book

import com.engineer_reviews.database.service.InitDBForTest
import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.valu_objects.*
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class BookTest {
    @Test
    fun testNew() {
        // create new Book instance from sent data
        val book = Book.new(sentData())

        // --------------------------------------

        // operation should be done as expected
        assertEquals("class com.engineer_reviews.model.book.Book", book.javaClass.toString())
    }



    // --------------------------------------

    companion object {
        fun entity(): Book {
            return Book(
                BookTitle("test"),
                BookPrice(1000),
                BookCategory.SERVER_SIDE(),
                BookReviewScore(5.5),
                BookUrl("https://www.amazon.co.jp/1")
            )
        }

        fun entity2(): Book {
            return Book(
                BookTitle("test2"),
                BookPrice(2000),
                BookCategory.FRONT_SIDE(),
                BookReviewScore(7.0),
                BookUrl("https://www.amazon.co.jp/2")
            )
        }

        fun sentData(): Book.Companion.SentData {
            return Book.Companion.SentData(
                "sent title",
                1000,
                2,
                3.0,
                "https://www.amazon.co.jp/sent"
            )
        }
    }

    // --------------------------------------

    @BeforeTest
    fun initDB() {
        InitDBForTest()
    }
}