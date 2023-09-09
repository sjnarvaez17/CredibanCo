package co.com.credibanco.domain.repository

import co.com.credibanco.domain.model.Credentials

interface CredentialsRepository {

    suspend fun saveCredentials(
        commerceCode: String,
        terminalCode: String
    )

    suspend fun getCredentials(
        commerceCode: String,
        terminalCode: String
    ): Credentials?
}
