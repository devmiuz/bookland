package uz.devmi.bookland.core.network

import retrofit2.http.GET
import retrofit2.http.Query


interface BookWebService {

    @GET(PAGEABLE_BOOKS)
    suspend fun getBooks(@Query("page") page: Int): PageableResult<BookResponse>

    private companion object{
        const val PAGEABLE_BOOKS = "books/"
    }
}