package co.com.credibanco.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorizationResponse(
val receiptId: String?,
val rrn: String?,
val statusCode: String?,
val statusDescription: String?
): Parcelable