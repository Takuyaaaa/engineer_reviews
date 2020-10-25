package book.value_objects

import com.engineer_reviews.model.book.valu_objects.BookUrl
import org.junit.Test
import kotlin.test.assertFails

class BookUrlTest {
    @Test
    fun testValidation() {
        // url's length must not be over 511
        assertFails {
            BookUrl("a".repeat(511))
        }

        // url must be a form of valid url
        assertFails {
            BookUrl("This should be invalid")
        }

        // url's length must be under 510 and be a form of valid url
        BookUrl("https://www.amazon.co.jp/?&tag=hydraamazonav-22&ref=pd_sl_7ibq2d37on_e&adgrpid=56100363354&hvpone=&hvptwo=&hvadid=289260145877&hvpos=&hvnetw=g&hvrand=11179919846289876199&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1009717&hvtargid=kwd-10573980&hydadcr=27922_11415158&gclid=CjwKCAjwoc_8BRAcEiwAzJevtTKuHIGzF9K1u2qF5XXO1sOVlEDnBdv_FF8Ue_uQNlSE7bwGguIs5BoCe2wQAvD_BwE")
    }
}