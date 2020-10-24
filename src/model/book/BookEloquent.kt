package com.engineer_reviews.model.book

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.model.book.Book
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class BookEloquent(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<BookEloquent>(Books)

    var title: String by Books.title
    var price: Int by Books.price
    var category: Int by Books.category
    var reviewScore: Double by Books.reviewScore
    var url: String by Books.url

    fun toEntity(): Book {
        val book = Book(this.title, this.price, this.category,
                this.reviewScore, this.url)

        book.id = this.id.value
        return book
    }
}