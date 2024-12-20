package uz.devmi.bookland.core.database

import uz.devmi.bookland.core.domain.Book

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        author = authors,
        imageUrl = coverImageUrl?:""
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity().apply {
        id = this@toEntity.id
        title = this@toEntity.title
        authors = this@toEntity.author
        coverImageUrl = this@toEntity.imageUrl
    }
}
