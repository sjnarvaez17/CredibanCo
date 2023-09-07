package co.com.credibanco.domain.repository

import co.com.credibanco.domain.model.Authorization

interface AuthorizationRepository {

    suspend fun requestAuthorization(
        id: String,
        commerceCode: String,
        terminalCode: String,
        amount: String,
        card: String
    ): Authorization?

    suspend fun getAuthorizedTransactionWithReceiptId(receiptId: String): Authorization?

    suspend fun getAllAuthorizedTransactions(): List<Authorization>
}
