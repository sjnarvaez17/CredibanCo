package co.com.credibanco.data.repository

import co.com.credibanco.data.source.local.LocalDataSource
import co.com.credibanco.data.source.local.entities.LoginEntity
import co.com.credibanco.data.source.local.entities.toCredentials
import co.com.credibanco.domain.model.Credentials
import co.com.credibanco.domain.repository.CredentialsRepository
import javax.inject.Inject

class CredentialsRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : CredentialsRepository {

    override suspend fun saveCredentials(
        commerceCode: String,
        terminalCode: String
    ) = localDataSource.saveCredentials(
        LoginEntity(commerceCode, terminalCode)
    )

    override suspend fun getCredentials(
        commerceCode: String,
        terminalCode: String
    ): Credentials? {
        val credentialsEntities = localDataSource
            .getCredentials(
                commerceCode,
                terminalCode
            )

        if (credentialsEntities.isEmpty()) {
            return null
        }

        return credentialsEntities[0].toCredentials()
    }
}
