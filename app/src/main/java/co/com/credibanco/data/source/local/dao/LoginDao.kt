package co.com.credibanco.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.credibanco.data.source.local.entities.COMMERCE_CODE_COLUMN_NAME
import co.com.credibanco.data.source.local.entities.LOGIN_TABLE_NAME
import co.com.credibanco.data.source.local.entities.LoginEntity
import co.com.credibanco.data.source.local.entities.TERMINAL_CODE_COLUMN_NAME

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCredentials(loginEntity: LoginEntity)

    @Query(
        "SELECT * FROM $LOGIN_TABLE_NAME " +
                "WHERE $COMMERCE_CODE_COLUMN_NAME = :commerceCode " +
                "AND $TERMINAL_CODE_COLUMN_NAME = :terminalCode"
    )
    fun getCredentials(
        commerceCode: String,
        terminalCode: String
    ): List<LoginEntity>
}
