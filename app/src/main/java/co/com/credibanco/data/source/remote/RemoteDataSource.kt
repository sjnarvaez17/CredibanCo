package co.com.credibanco.data.source.remote

import co.com.credibanco.data.source.remote.response.AnnulmentRemoteResponse
import co.com.credibanco.data.source.remote.response.AuthorizationRemoteResponse

interface RemoteDataSource {

    suspend fun fetchAuthorization(
        id: String,
        commerceCode: String,
        terminalCode: String,
        amount: String,
        card: String
    ): AuthorizationRemoteResponse

    suspend fun requestAnnulment(
        receiptId: String,
        rrn: String
    ): AnnulmentRemoteResponse
}
