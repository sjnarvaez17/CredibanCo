package co.com.credibanco.di

import co.com.credibanco.data.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val HEADER_NAME = "Authorization"
    private const val HEADER_VALUE = "Basic MDAwMTIzMDAwQUJD"

    private var apiService: ApiService? = null

    fun getApiService(): ApiService {
        apiService?.let { return it }

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.REST_BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(createConverterFactory())
            .build()

        return retrofit.create(ApiService::class.java).also { apiService = it }
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )

        return OkHttpClient.Builder()
            .cache(null)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()

                val newRequest = request.newBuilder()
                    .addHeader(HEADER_NAME, HEADER_VALUE)
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    private fun createConverterFactory(): Converter.Factory = GsonConverterFactory.create()
}
