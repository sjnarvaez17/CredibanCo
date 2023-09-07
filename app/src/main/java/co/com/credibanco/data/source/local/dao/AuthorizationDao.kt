package co.com.credibanco.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.credibanco.data.source.local.entities.AUTHORIZATION_TABLE_NAME
import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import co.com.credibanco.data.source.local.entities.RECEIPT_ID_COLUMN_NAME

@Dao
interface AuthorizationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuthorization(vararg authorizationEntity: AuthorizationEntity)

    @Query("SELECT * FROM $AUTHORIZATION_TABLE_NAME ORDER BY $RECEIPT_ID_COLUMN_NAME")
    fun getAllAuthorizations(): List<AuthorizationEntity>
}
