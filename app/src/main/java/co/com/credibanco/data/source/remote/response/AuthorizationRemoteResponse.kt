package co.com.credibanco.data.source.remote.response

import android.os.Parcelable
import co.com.credibanco.domain.model.Authorization
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorizationRemoteResponse(
    val receiptId: String?,
    val rrn: String?,
    val statusCode: String?,
    val statusDescription: String?
) : Parcelable

fun AuthorizationRemoteResponse.toAuthorization() = if (
    receiptId.isNullOrBlank() ||
    rrn.isNullOrBlank() ||
    statusCode.isNullOrBlank() ||
    statusDescription.isNullOrBlank()
) {
    null
} else {
    Authorization(receiptId, rrn, statusCode, statusDescription)
}
