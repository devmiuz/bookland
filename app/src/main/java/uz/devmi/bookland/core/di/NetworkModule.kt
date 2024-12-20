package uz.devmi.bookland.core.di

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import uz.devmi.bookland.core.network.BookWebService
import uz.devmi.bookland.core.network.RetrofitInstance

@Module
class NetworkModule {

    @Single
    fun provideRetrofit(): Retrofit {
        return RetrofitInstance.createRetrofit()
    }

    @Single
    fun provideBookApi(retrofit: Retrofit): BookWebService {
        return retrofit.create(BookWebService::class.java)
    }
}
