package com.engineer_reviews.database.dao

import org.jetbrains.exposed.dao.IntIdTable

object Books : IntIdTable("books") {
    var title = varchar("title", 255).uniqueIndex()
    var price = integer("price")
    var category = integer("category")
    var reviewScore = double("review_score")
    var url = varchar("url", 510)
    var imagePath = varchar("image_path", 510)
}