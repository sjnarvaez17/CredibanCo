package co.com.credibanco.domain.repository

import co.com.credibanco.domain.model.Authorization

interface AuthorizationRepository {

    suspend fun fetchAuthorization(
        id: String,
        commerceCode: String,
        terminalCode: String,
        amount: String,
        card: String
    ): Authorization
}