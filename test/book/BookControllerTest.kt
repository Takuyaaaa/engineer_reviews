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

    @Test
    fun testShow(): Unit = withTestApplication(Application::module) {
        // save entity
        val book = BookRepository.save(BookRepositoryTest.entity())
        val bookId = book.id

        // --------------------------------------

        handleRequest(HttpMethod.Get, "/book/${bookId?.value}").run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(book.id?.value.toString(),
                    ObjectMapper().readTree(response.content).get("id").get("value").toString())
        }
    }

    @Test
    fun testUpdate(): Unit = withTestApplication(Application::module) {
        // save entity
        val book = BookRepository.save(BookRepositoryTest.entity())
        val bookId = book.id

        // --------------------------------------

        val putData = ObjectMapper().writeValueAsString(BookRepositoryTest.entity2())
        handleRequest(HttpMethod.Put, "/book/${bookId?.value}") {
            addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            setBody(putData)
        }.run {
            // operation should be done as expected
            assertEquals(HttpStatusCode.OK, response.status())
            val response = ObjectMapper().readTree(response.content)
            assertEquals(BookRepositoryTest.entity2().title, response.get("title").textValue())
            assertEquals(BookRepositoryTest.entity2().price.toString(), response.get("price").toString())
            assertEquals(BookRepositoryTest.entity2().category.toString(), response.get("category").toString())
            assertEquals(BookRepositoryTest.entity2().reviewScore.toString(), response.get("reviewScore").toString())
            assertEquals(BookRepositoryTest.entity2().url, response.get("url").textValue())
        }
    }

    @Test
    fun testDelete(): Unit = withTestApplication(Application::module) {
        // save entity
        val book = BookRepository.save(BookRepositoryTest.entity())
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