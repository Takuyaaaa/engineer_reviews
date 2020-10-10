package com.engineer_reviews.models.book

import com.engineer_reviews.database.dao.Books
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class BookEloquent(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<BookEloquent>(Books)

    var title by Books.title
    var price by Books.price
    var category by Books.category
    var reviewScore by Books.reviewScore
    var url by Books.url

    fun toEntity(): Book {
        return Book(this.title, this.price, this.category,
                this.reviewScore, this.url)
    }
}