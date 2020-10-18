package book

import com.engineer_reviews.book.BookRepositoryTest
import com.engineer_reviews.module
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BookControllerTest {
    @Test
    fun testIndex(): Unit = withTestApplication(Application::module) {
        handleRequest(HttpMethod.Get, "/book").run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
        }
    }

    @Test
    fun testCreate(): Unit = withTestApplication(Application::module) {
        val postData = ObjectMapper().writeValueAsString(BookRepositoryTest.entity())
        handleRequest(HttpMethod.Post, "/book") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(postData)
        }.run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
        }
    }
}