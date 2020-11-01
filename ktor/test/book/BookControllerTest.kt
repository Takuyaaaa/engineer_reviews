package book

import com.engineer_reviews.model.book.BookRepository
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
        val postData = ObjectMapper().writeValueAsString(
            mapOf("title" to "New Book", "price" to 3000, "category" to 1, "reviewScore" to 2.0, "url" to "https://www.amazon.co.jp", "imagePath" to "this/is/test/path"))

        handleRequest(HttpMethod.Post, "/book") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(postData)
        }.run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
        }
    }

    @Test
    fun testShow(): Unit = withTestApplication(Application::module) {
        // save entity
        val book = BookRepository.save(BookTest.entity())
        val bookId = book.id

        // --------------------------------------

        handleRequest(HttpMethod.Get, "/book/${bookId?.value}").run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            println(response.content)
            assertEquals(book.id?.value.toString(), ObjectMapper().readTree(response.content).get("id").get("value").toString())
        }
    }

    @Test
    fun testUpdate(): Unit = withTestApplication(Application::module) {
        // save entity
        val book = BookRepository.save(BookTest.entity())
        val bookId = book.id

        // --------------------------------------

        val putData = ObjectMapper().writeValueAsString(
            mapOf("title" to "Updated Book", "price" to 3000, "category" to 1, "reviewScore" to 2.0, "url" to "https://www.amazon.co.jp", "imagePath" to "this/is/test/path"))

        handleRequest(HttpMethod.Put, "/book/${bookId?.value}") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(putData)
        }.run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            val response = ObjectMapper().readTree(response.content)
            assertEquals("Updated Book", response.get("title").get("value").textValue())
            assertEquals("3000", response.get("price").get("value").toString())
            assertEquals("1", response.get("category").get("value").toString())
            assertEquals("2.0", response.get("reviewScore").get("value").toString())
            assertEquals("https://www.amazon.co.jp", response.get("url").get("value").textValue())
        }
    }

    @Test
    fun testDelete(): Unit = withTestApplication(Application::module) {
        // save entity
        val book = BookRepository.save(BookTest.entity())
        val bookId = book.id

        // --------------------------------------

        handleRequest(HttpMethod.Delete, "/book/${bookId?.value}").run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(book.id?.value.toString(),
                ObjectMapper().readTree(response.content).get("id").get("value").toString())
        }
    }
}