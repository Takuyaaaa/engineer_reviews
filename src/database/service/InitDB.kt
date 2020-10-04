package com.engineer_reviews.database.service

import org.jetbrains.exposed.sql.Database

fun InitDB() {
    Database.connect(
    "jdbc:mysql//localhost/engineer_reviews",
  "com.myswl.jdbc.driver",
        user = "user",
        password = "password"
    )
}