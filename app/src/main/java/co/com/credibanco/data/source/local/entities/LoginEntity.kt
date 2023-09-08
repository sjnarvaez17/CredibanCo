package co.com.credibanco.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.Base64

const val LOGIN_TABLE_NAME = "login"
const val COMMERCE_CODE_COLUMN_NAME = "commerceCode"
const val TERMINAL_CODE_COLUMN_NAME = "terminalCode"

@Entity(
    tableName = LOGIN_TABLE_NAME,
    primaryKeys = [COMMERCE_CODE_COLUMN_NAME, TERMINAL_CODE_COLUMN_NAME]
)
data class LoginEntity(
    @ColumnInfo(name = COMMERCE_CODE_COLUMN_NAME) val commerceCode: String,
    @ColumnInfo(name = TERMINAL_CODE_COLUMN_NAME) val terminalCode: String,
    val token: String = Base64
        .getEncoder()
        .encodeToString(
            "$commerceCode$terminalCode".toByteArray()
        )
)
