package co.com.credibanco.data.repository

import co.com.credibanco.data.source.local.LocalDataSource
import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import co.com.credibanco.data.source.local.entities.toAuthorization
import co.com.credibanco.data.source.remote.RemoteDataSource
import co.com.credibanco.data.source.remote.response.toAuthorization
import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.domain.repository.AuthorizationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AuthorizationRepository {

    companion object {

        private const val STATUS_APPROVE = "Aprobada"
    }

    override suspend fun requestAuthorization(
        id: String,
        commerceCode: String,
        terminalCode: String,
        amount: String,
        card: String
    ): Authorization? {

        val authorizationResponse = remoteDataSource.requestAuthorization(
            id,
            commerceCode,
            terminalCode,
            amount,
            card
        )

        return authorizationResponse.toAuthorization().also {
            if (it?.statusDescription == STATUS_APPROVE) {
                localDataSource.saveAuthorization(
                    AuthorizationEntity(
                        it.receiptId,
                        it.rrn,
                        it.statusCode,
                        it.statusDescription
                    )
                )
            }
        }
    }

    override suspend fun getAuthorizedTransactionWithReceiptId(receiptId: String): Authorization? {
        val transactions: List<AuthorizationEntity> = localDataSource
            .fetchAuthorizationWithReceiptId(receiptId)

        if (transactions.isEmpty()) {
            return null
        }

        return transactions[0].toAuthorization()
    }

    override suspend fun getAllAuthorizedTransactions() = localDataSource
        .getAllAuthorizedTransactions()
        .map {
            it.toAuthorization()
        }
}
