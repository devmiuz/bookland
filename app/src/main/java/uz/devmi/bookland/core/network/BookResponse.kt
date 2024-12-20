package uz.devmi.bookland.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("authors")
    val authors: List<Author>,
    @SerialName("formats")
    val formats: Formats
)

@Serializable
data class Formats(
    @SerialName("image/jpeg")
    val coverImage: String?
)

@Serializable
data class Author(
    @SerialName("name")
    val name: String?
)
