package co.com.credibanco.data.source.remote.impl

import co.com.credibanco.data.source.remote.RemoteDataSource
import co.com.credibanco.data.source.remote.request_body.AnnulmentRequestBody
import co.com.credibanco.data.source.remote.request_body.AuthorizationRequestBody
import co.com.credibanco.data.source.remote.response.AnnulmentRemoteResponse
import co.com.credibanco.data.source.remote.response.AuthorizationRemoteResponse
import co.com.credibanco.di.NetworkModule
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor() : RemoteDataSource {

    override suspend fun fetchAuthorization(
        id: String,
        commerceCode: String,
        terminalCode: String,
        amount: String,
        card: String
    ): AuthorizationRemoteResponse {
        val authorizationRequestBody = AuthorizationRequestBody(
            id,
            commerceCode,
            terminalCode,
            amount,
            card
        )

        return NetworkModule.getApiService().requestAuthorization(authorizationRequestBody)
    }

    override suspend fun requestAnnulment(receiptId: String, rrn: String): AnnulmentRemoteResponse {
        val annulmentRequestBody = AnnulmentRequestBody(receiptId, rrn)

        return NetworkModule.getApiService().requestAnnulment(annulmentRequestBody)
    }
}
