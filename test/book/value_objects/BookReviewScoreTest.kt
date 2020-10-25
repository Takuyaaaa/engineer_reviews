package book.value_objects

import com.engineer_reviews.model.book.valu_objects.BookReviewScore
import org.junit.Test
import kotlin.test.assertFails

class BookReviewScoreTest {
    @Test
    fun testValidation() {
        // review score must not be negative
        assertFails {
            BookReviewScore(-1.0)
        }

        // review score must not be over 10.0
        assertFails {
            BookReviewScore(10.1)
        }

        // review score must be between 0.0 and 10.0
        BookReviewScore(0.0)
        BookReviewScore(10.0)
    }
}