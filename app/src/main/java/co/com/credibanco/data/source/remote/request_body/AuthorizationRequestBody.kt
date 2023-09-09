package co.com.credibanco.data.source.remote.request_body

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorizationRequestBody(
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String
) : Parcelable
