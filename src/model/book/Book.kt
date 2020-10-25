package com.engineer_reviews.model.book

import com.engineer_reviews.model.book.valu_objects.*

class Book(
    var title: BookTitle,
    var price: BookPrice,
    var category: BookCategory,
    var reviewScore: BookReviewScore,
    var url: String) {

        var id: BookId? = null

        fun isEqual(other: Book): Boolean {
            // if entity is not saved (i.e not having id) return false
            if (this.id === null) {
                return false
            }
            if (other.id === null) {
                return false
            }

            // if both ids are same return true
            return this.id?.value === other.id?.value
        }
}