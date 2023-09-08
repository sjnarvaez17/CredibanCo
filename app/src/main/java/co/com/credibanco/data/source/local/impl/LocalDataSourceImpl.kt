package co.com.credibanco.data.source.local.impl

import co.com.credibanco.data.AppDatabase
import co.com.credibanco.data.source.local.LocalDataSource
import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import co.com.credibanco.data.source.local.entities.LoginEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) :
    LocalDataSource {

    override suspend fun saveCredentials(loginEntity: LoginEntity) = appDatabase
        .loginDao()
        .insertCredentials(loginEntity)

    override suspend fun getCredentials(
        commerceCode: String,
        terminalCode: String
    ) = appDatabase
        .loginDao()
        .getCredentials(commerceCode, terminalCode)

    override suspend fun saveAuthorization(
        vararg authorizationEntity: AuthorizationEntity
    ) = appDatabase
        .authorizationDao()
        .insertAuthorization(*authorizationEntity)

    override suspend fun fetchAuthorizationWithReceiptId(receiptId: String) = appDatabase
        .authorizationDao()
        .getAuthorizationWithReceiptId(receiptId)

    override suspend fun getAllAuthorizedTransactions() = appDatabase
        .authorizationDao()
        .getAllAuthorizations()
}
