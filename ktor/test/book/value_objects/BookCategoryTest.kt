package book.value_objects

import com.engineer_reviews.model.book.value_objects.BookCategory
import org.junit.Test
import kotlin.test.assertFails

class BookCategoryTest {
    @Test
    fun testValidation() {
        // category must not be under 1
        assertFails {
            BookCategory(0)
        }

        // category must not be over 6
        assertFails {
            BookCategory(7)
        }

        // category must not be between 1 and 6
        BookCategory(1)
        BookCategory(6)
    }
}