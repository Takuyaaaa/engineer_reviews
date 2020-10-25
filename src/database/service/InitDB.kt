package com.engineer_reviews.database.service

import com.engineer_reviews.database.dao.Books
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