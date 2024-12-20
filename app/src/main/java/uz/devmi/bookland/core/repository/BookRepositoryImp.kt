package uz.devmi.bookland.core.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import uz.devmi.bookland.core.database.BookEntity
import uz.devmi.bookland.core.database.toDomain
import uz.devmi.bookland.core.domain.Book
import uz.devmi.bookland.core.network.BookWebService
import uz.devmi.bookland.core.network.toEntity
import java.net.ConnectException
import java.net.UnknownHostException

@Single
class BookRepositoryImp(
    private val realm: Realm,
    private val bookWebService: BookWebService,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BookRepository {

    override suspend fun getCachedBookById(id: Int): Result<Book> = withContext(coroutineDispatcher) {
        runCatching {
            val bookEntity = realm.query<BookEntity>("id == $0", id).first().find()
                ?: throw Exception("Book with ID $id not found in cache")
            bookEntity.toDomain()
        }
    }

    override suspend fun fetchBooks(page: Int): Result<List<Book>> =
        withContext(Dispatchers.IO) {
            try {
                val apiResponse = bookWebService.getBooks(page)
                val bookEntities = apiResponse.results.map { it.toEntity() }
                saveBooksToCache(bookEntities)
                Result.success(
                    getCachedBooks(page, 32).getOrDefault(listOf())
                )
            } catch (exception: Exception) {
                if (exception is ConnectException || exception is UnknownHostException) {
                    // Handle no connection scenario and fallback to cache
                    val cachedBooks = realm.query<BookEntity>().find().map { it.toDomain() }
                    if (cachedBooks.isNotEmpty()) {
                        Result.success(
                            getCachedBooks(page, 32).getOrDefault(listOf())
                        )
                    } else {
                        Result.failure(Exception("No internet connection and no cached data available"))
                    }
                } else {
                    Result.failure(exception)
                }
            }
        }

    private suspend fun getCachedBooks(page: Int, pageSize: Int): Result<List<Book>> =
        withContext(Dispatchers.IO) {
            runCatching {
                val cachedBooks = realm.query<BookEntity>()
                    .limit(pageSize) // Fetch only the required page size
//                    .skip((page - 1) * pageSize) // Skip rows based on the current page
                    .find()
                    .map { it.toDomain() }
                if (cachedBooks.isNotEmpty()) cachedBooks
                else throw Exception("No books found for page $page")
            }
        }

    private suspend fun saveBooksToCache(books: List<BookEntity>) {
        withContext(coroutineDispatcher) {
            realm.write {
                books.forEach { entity ->
                    copyToRealm(instance = entity, updatePolicy = UpdatePolicy.ALL)
                }
            }
        }
    }
}