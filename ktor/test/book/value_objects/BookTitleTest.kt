package book.value_objects

import com.engineer_reviews.model.book.value_objects.BookTitle
import org.junit.Test
import kotlin.test.assertFails

class BookTitleTest {
    @Test
    fun testValidation() {
        // title's length must not be over 255
        assertFails {
            BookTitle("a".repeat(256))
        }

        // title's length must be under 255
        BookTitle("a".repeat(255))
    }
}