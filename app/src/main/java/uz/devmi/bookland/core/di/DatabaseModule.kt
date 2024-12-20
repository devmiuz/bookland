package uz.devmi.bookland.core.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import uz.devmi.bookland.core.database.BookEntity

@Module
class DatabaseModule {

    @Single
    fun provideRealmConfig(): RealmConfiguration {
        return RealmConfiguration.Builder(
            schema = setOf(BookEntity::class)
        )
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    @Single
    fun provideRealmInstance(config: RealmConfiguration): Realm {
        return Realm.open(config)
    }
}
