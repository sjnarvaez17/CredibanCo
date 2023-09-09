package co.com.credibanco.di

import co.com.credibanco.data.ApiService
import co.com.credibanco.domain.model.Credentials
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val HEADER_NAME = "Authorization"
    private const val HEADER_VALUE = "Basic %s"

    private var apiService: ApiService? = null
    var credentials: Credentials? = null

    fun getApiService(): ApiService {
        apiService?.let { return it }

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.REST_BASE_URL)
            .client(createOkHttpClient(credentials))
            .addConverterFactory(createConverterFactory())
            .build()

        return retrofit.create(ApiService::class.java).also { apiService = it }
    }

    private fun createOkHttpClient(credentials: Credentials?): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )

        val authenticator = CustomAuthenticator(credentials)

        return OkHttpClient.Builder()
            .cache(null)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = setUpRequestInterceptor(chain)
                chain.proceed(request)
            }
            .authenticator(authenticator)
            .build()
    }

    private fun setUpRequestInterceptor(chain: Interceptor.Chain) = chain
        .request()
        .newBuilder()
        .addHeader(
            name = HEADER_NAME,
            value = HEADER_VALUE.format(credentials?.token)
        )
        .build()

    private fun createConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    private class CustomAuthenticator(private val credentials: Credentials?) : Authenticator {

        override fun authenticate(route: Route?, response: Response): Request? {
            val token = credentials?.token ?: return null

            return response
                .request
                .newBuilder()
                .header(
                    name = HEADER_NAME,
                    value = HEADER_VALUE.format(token)
                )
                .build()
        }
    }
}
