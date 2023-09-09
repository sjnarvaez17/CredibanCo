package co.com.credibanco.data

import co.com.credibanco.data.source.remote.request_body.AnnulmentRequestBody
import co.com.credibanco.data.source.remote.request_body.AuthorizationRequestBody
import co.com.credibanco.data.source.remote.response.AnnulmentRemoteResponse
import co.com.credibanco.data.source.remote.response.AuthorizationRemoteResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    companion object {

        const val REST_BASE_URL = "http://localhost:8080"
        private const val PARTICLE_AUTHORIZATION = "/api/payments/authorization"
        private const val PARTICLE_ANNULMENT = "/api/payments/annulment"
    }

    @POST(PARTICLE_AUTHORIZATION)
    suspend fun requestAuthorization(
        @Body authorizationRequestBody: AuthorizationRequestBody
    ): AuthorizationRemoteResponse

    @POST(PARTICLE_ANNULMENT)
    suspend fun requestAnnulment(
        @Body annulmentRequestBody: AnnulmentRequestBody
    ): AnnulmentRemoteResponse
}
