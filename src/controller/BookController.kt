package com.engineer_reviews.controller

import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository

class BookController {

    fun index(): List<Book> {
        // todo: "toEntity" should be handled by repository
        return BookRepository.getAll().map { it.toEntity() }
    }

    fun create(newBook: Book): Book {
        return BookRepository.save(newBook)
    }

    fun show(id: Int): Book? {
        return BookRepository.find(id)
    }

    fun update(id: Int, book: Book): Book {
        return BookRepository.update(id, book)
    }

    fun delete(id: Int): Book? {
        return BookRepository.delete(id)
    }
}