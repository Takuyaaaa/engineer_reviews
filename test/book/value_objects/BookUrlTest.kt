package book.value_objects

import com.engineer_reviews.model.book.valu_objects.BookUrl
import org.junit.Test
import kotlin.test.assertFails

class BookUrlTest {
    @Test
    fun testValidation() {
        // url's length must not be over 255
        assertFails {
            BookUrl("a".repeat(256))
        }

        // url must be a form of valid url
//        assertFails {
//            BookUrl("This should be invalid")
//        }
//
//        BookUrl("https://this/should/be/valid")
    }
}