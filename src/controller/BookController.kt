package com.engineer_reviews.controller

import com.engineer_reviews.model.book.Book
import com.engineer_reviews.model.book.BookRepository
import com.engineer_reviews.model.book.valu_objects.BookId

class BookController {

    fun index(): List<Book> {
        // todo: "toEntity" should be handled by repository
        return BookRepository.getAll().map { it.toEntity() }
    }

    fun create(newBook: Book): Book {
        return BookRepository.save(newBook)
    }

    fun show(id: Int): Book? {
        return BookRepository.find(BookId(id))
    }

    fun update(id: Int, book: Book): Book {
        return BookRepository.update(BookId(id), book)
    }

    fun delete(id: Int): Book? {
        return BookRepository.delete(BookId(id))
    }
}