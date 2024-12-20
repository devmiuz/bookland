package uz.devmi.bookland.core.network

import retrofit2.http.GET
import retrofit2.http.Query


interface BookWebService {

    @GET("books/")
    suspend fun getBooks(@Query("page") page: Int): PageableResult<BookResponse>
}