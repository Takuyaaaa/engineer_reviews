package com.engineer_reviews.controllers

import com.engineer_reviews.models.book.Book
import com.engineer_reviews.models.book.BookRepository

class BookController {

    fun index(): List<Book> {
        return BookRepository.getAll().map { it.toEntity() }
    }

    fun create(newBook: Book): Book {
        BookRepository.save(newBook)
        return newBook
    }

    fun show(id: Int): Book {
        return BookRepository.find(id)
    }

    fun update(id: Int, book: Book): Book {
        return BookRepository.update(id, book)
    }

    fun delete(id: Int): Book {
        return BookRepository.delete(id)
    }
}