package co.com.credibanco.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.com.credibanco.domain.model.Authorization

const val AUTHORIZATION_TABLE_NAME = "authorization"
const val RECEIPT_ID_COLUMN_NAME = "receiptId"

@Entity(tableName = AUTHORIZATION_TABLE_NAME)
data class AuthorizationEntity(
    @PrimaryKey @ColumnInfo(name = RECEIPT_ID_COLUMN_NAME) val receiptId: String,
    val rrn: String,
    val statusCode: String,
    val statusDescription: String
)

fun AuthorizationEntity.toAuthorization() = Authorization(
    receiptId,
    rrn,
    statusCode,
    statusDescription
)
