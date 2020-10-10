package com.engineer_reviews.database.service

import org.jetbrains.exposed.sql.Database

fun InitDB() {
    Database.connect(
        url = "jdbc:mariadb://localhost:3306/engineer_reviews",
        driver = "org.mariadb.jdbc.Driver",
        user = "root",
        password = "password"
    )
}