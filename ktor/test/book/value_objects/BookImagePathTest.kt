package book.value_objects

import com.engineer_reviews.model.book.value_objects.BookImagePath
import org.junit.Test
import kotlin.test.assertFails

class BookImagePathTest {
    @Test
    fun testValidation() {
        // image path's length must not be over 511
        assertFails {
            BookImagePath("a".repeat(511))
        }

        // image path's length must be under or equal 510
        BookImagePath("a".repeat(510))
    }
}