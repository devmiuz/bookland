package uz.devmi.bookland.core.database

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class BookEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var authors: String = "" // Comma-separated list of authors
    var coverImageUrl: String? = null
}
