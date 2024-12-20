package uz.devmi.bookland.core.network

import uz.devmi.bookland.core.database.BookEntity
import uz.devmi.bookland.core.domain.Book


fun BookResponse.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        author = authors.joinToString(", "),
        imageUrl = formats.coverImage ?: ""
    )
}

fun BookResponse.toEntity(): BookEntity {
    return BookEntity().apply {
        id = this@toEntity.id
        title = this@toEntity.title
        authors = this@toEntity.authors.map { it.name }.joinToString(", ")
        coverImageUrl = this@toEntity.formats.coverImage
    }
}
