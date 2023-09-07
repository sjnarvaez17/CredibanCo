package co.com.credibanco.data.source.local

import co.com.credibanco.data.source.local.entities.AuthorizationEntity

interface LocalDataSource {

    suspend fun saveAuthorization(vararg authorizationEntity: AuthorizationEntity)

    suspend fun fetchAuthorizationWithReceiptId(receiptId: String): List<AuthorizationEntity>

    suspend fun getAllAuthorizedTransactions(): List<AuthorizationEntity>
}
