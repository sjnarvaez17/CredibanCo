package co.com.credibanco.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorizationRequest(
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String
) : Parcelable

@Parcelize
data class AnnulmentRequest(
    val receiptId: String,
    val rrn: String
) : Parcelable