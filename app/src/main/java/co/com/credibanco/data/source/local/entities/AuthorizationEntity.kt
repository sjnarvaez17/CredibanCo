package co.com.credibanco.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.com.credibanco.domain.model.Authorization

const val AUTHORIZATION_TABLE_NAME= "authorization"


@Entity(tableName = AUTHORIZATION_TABLE_NAME)
data class AuthorizationEntity(
    @PrimaryKey
    val receiptId: String,
    @ColumnInfo
    val rrn: String,
    @ColumnInfo
    val statusCode: String,
    @ColumnInfo
    val statusDescription: String
)

fun AuthorizationEntity.toAuthorization() = Authorization(receiptId, rrn, statusCode, statusDescription)