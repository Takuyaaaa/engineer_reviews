package com.engineer_reviews.model.book

class Book(
        var title: String,
        var price: Int,
        var category: Int,
        var reviewScore: Double,
        var url: String) {

        var id: Int? = null

        fun isEqual(other: Book): Boolean {
            // if entity is not saved (i.e not having id) return false
            if (this.id === null) {
                return false
            }
            if (other.id === null) {
                return false
            }

            // if both ids are same return true
            return this.id === other.id
        }
}