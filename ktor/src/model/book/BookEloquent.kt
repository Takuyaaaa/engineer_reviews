package com.engineer_reviews.model.book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.model.book.value_objects.*
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class BookEloquent(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BookEloquent>(Books)

    var title: String by Books.title
    var price: Int by Books.price
    var category: Int by Books.category
    var reviewScore: Double by Books.reviewScore
    var url: String by Books.url

    fun toEntity(): Book {
        val book = Book(
            BookTitle(this.title),
            BookPrice(this.price),
            BookCategory(this.category),
            BookReviewScore(this.reviewScore),
            BookUrl(this.url))

        book.id = BookId(this.id.value)
        return book
    }
}