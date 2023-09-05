package co.com.credibanco.data

import retrofit2.http.Body
import retrofit2.http.POST


object ApiUrl {
        const val REST_BASE_URL = "http://localhost:8080/api/payments"
        const val AUTHORIZATION = "/authorization"
        const val ANNULMENT = "/annulment"
    }

class ApiService {

    @POST(ApiUrl.AUTHORIZATION)
    suspend fun fetchAutorization(@Body autorizationRequest: AuthorizationRequest) {
    }

    @POST(ApiUrl.ANNULMENT)
    suspend fun fetchAnnulment(@Body annulmentRequest: AnnulmentRequest){

    }

}