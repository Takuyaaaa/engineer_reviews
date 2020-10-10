package com.engineer_reviews.models.book

import com.engineer_reviews.database.dao.Books
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Book(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Book>(Books)

    var title by Books.title
    var price by Books.price
    var category by Books.category
    var reviewScore by Books.reviewScore
    var bookUrl by Books.bookUrl
}

data class BookJson(
    val title: String,
    val price: Int,
    val category: Int,
    val reviewScore: Int,
    val bookUrl: String)