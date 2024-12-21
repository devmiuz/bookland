package uz.devmi.bookland.core.repository

import uz.devmi.bookland.core.domain.Book

interface BookRepository {
    suspend fun getBooks(
        page: Int,
        size: Int,
        lastEntityId: Int
    ): Result<List<Book>>

    suspend fun getCachedBookById(id: Int): Result<Book>
}