package com.engineer_reviews.database.dao

import org.jetbrains.exposed.dao.IntIdTable

object Books: IntIdTable() {
    val title = varchar("title", 50).uniqueIndex()
    var category = integer("category")
    var reviewScore = integer("review_score")
    var bookUrl = varchar("book_url", 255)
}