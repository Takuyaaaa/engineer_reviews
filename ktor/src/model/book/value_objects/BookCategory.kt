package com.engineer_reviews.model.book.value_objects

class BookCategory(val value: Int) {

    init {
        if (value < 1 || value > 6) {
            throw Exception("VALIDATE FAILED")
        }
    }

    companion object {
        fun SERVER_SIDE(): BookCategory {
            return BookCategory(1)
        }

        fun FRONT_SIDE(): BookCategory {
            return BookCategory(2)
        }

        fun INFRASTORUCTURE(): BookCategory {
            return BookCategory(3)
        }

        fun OS(): BookCategory {
            return BookCategory(4)
        }

        fun HARDWARE(): BookCategory {
            return BookCategory(5)
        }

        fun DESIGN(): BookCategory {
            return BookCategory(6)
        }

        fun NETWORK(): BookCategory {
            return BookCategory(6)
        }

        fun DATA_SCIENCE(): BookCategory {
            return BookCategory(6)
        }
    }
}