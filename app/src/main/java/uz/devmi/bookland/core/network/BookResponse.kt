package uz.devmi.bookland.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val id: Int,
    val title: String,
    val authors: List<Author>,
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
