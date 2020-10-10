package com.engineer_reviews.database.dao

import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.exposed.dao.IntIdTable

object Books: IntIdTable("books") {
    var title = varchar("title", 50).uniqueIndex()
    var price = integer("price")
    var category = integer("category")
    var reviewScore = integer("review_score")
    var url = varchar("url", 255)
}