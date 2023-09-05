package co.com.credibanco.data

import co.com.credibanco.data.source.remote.request_body.AnnulmentRequestBody
import co.com.credibanco.data.source.remote.request_body.AuthorizationRequestBody
import co.com.credibanco.data.source.remote.response.AnnulmentResponse
import co.com.credibanco.data.source.remote.response.AuthorizationResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    companion object {

        const val REST_BASE_URL = "http://localhost:8080/api/payments"
        private const val PARTICLE_AUTHORIZATION = "/authorization"
        private const val PARTICLE_ANNULMENT = "/annulment"
    }

    @POST(PARTICLE_AUTHORIZATION)
    suspend fun requestAuthorization(
        @Body authorizationRequestBody: AuthorizationRequestBody
    ): AuthorizationResponse

    @POST(PARTICLE_ANNULMENT)
    suspend fun requestAnnulment(
        @Body annulmentRequestBody: AnnulmentRequestBody
    ): AnnulmentResponse
}
