package com.engineer_reviews.database.service

import com.engineer_reviews.database.dao.Books
import com.engineer_reviews.model.book.BookEloquent
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun InitDB() {
    Database.connect(
        url = "jdbc:mariadb://localhost:3306/engineer_reviews",
        driver = "org.mariadb.jdbc.Driver",
        user = "root",
        password = "password"
    )

    // create Books Table
    transaction {
        SchemaUtils.drop(Books)
        SchemaUtils.create(Books)

        BookEloquent.new {
            title = "title"
            price = 3000
            category = 1
            reviewScore = 3.1
            url = "https://amazon.co.jp"
            imagePath = "/Users/takuya/Desktop/profile-picture-1586228238.jpg"
        }

        BookEloquent.new {
            title = "title2"
            price = 4000
            category = 2
            reviewScore = 4.0
            url = "https://amazon.co.jp"
            imagePath = "/Users/takuya/Desktop/profile-picture-1586228238.jpg"
        }
    }
}

fun InitDBForTest() {
    Database.connect(
        url = "jdbc:mariadb://localhost:3306/engineer_reviews_test",
        driver = "org.mariadb.jdbc.Driver",
        user = "root",
        password = "password"
    )

    // create Books Table
    transaction {
        SchemaUtils.drop(Books)
        SchemaUtils.create(Books)
    }
}