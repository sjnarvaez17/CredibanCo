package co.com.credibanco.data.source.local.impl

import co.com.credibanco.data.AppDatabase
import co.com.credibanco.data.source.local.LocalDataSource
import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) :
    LocalDataSource {

    override suspend fun saveAuthorization(vararg authorizationEntity: AuthorizationEntity) =
        appDatabase.authorizationDao().insertAuthorization(*authorizationEntity)

    override suspend fun fetchAuthorizationList(): List<AuthorizationEntity> =
        appDatabase.authorizationDao().getAllAuthorizations()
}
