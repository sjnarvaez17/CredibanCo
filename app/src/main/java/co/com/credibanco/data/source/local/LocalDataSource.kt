package co.com.credibanco.data.source.local

import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import co.com.credibanco.data.source.local.entities.LoginEntity

interface LocalDataSource {

    suspend fun saveCredentials(loginEntity: LoginEntity)

    suspend fun getCredentials(
        commerceCode: String,
        terminalCode: String
    ): List<LoginEntity>

    suspend fun saveAuthorization(vararg authorizationEntity: AuthorizationEntity)

    suspend fun fetchAuthorizationWithReceiptId(receiptId: String): List<AuthorizationEntity>

    suspend fun getAllAuthorizedTransactions(): List<AuthorizationEntity>
}
