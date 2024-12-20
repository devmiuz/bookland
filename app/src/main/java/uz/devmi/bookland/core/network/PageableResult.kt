package uz.devmi.bookland.core.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageableResult<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerialName("results")
    val results: List<T>
)
