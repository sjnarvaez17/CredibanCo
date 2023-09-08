package co.com.credibanco.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.credibanco.data.source.local.dao.AuthorizationDao
import co.com.credibanco.data.source.local.dao.LoginDao
import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import co.com.credibanco.data.source.local.entities.LoginEntity

@Database(
    entities = [
        AuthorizationEntity::class,
        LoginEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "credibanco-local"
    }

    abstract fun authorizationDao(): AuthorizationDao

    abstract fun loginDao(): LoginDao
}
