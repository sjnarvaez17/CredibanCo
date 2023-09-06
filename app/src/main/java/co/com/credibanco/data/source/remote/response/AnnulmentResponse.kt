package co.com.credibanco.data.source.remote.response

import android.os.Parcelable
import co.com.credibanco.domain.model.Annulment
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnnulmentResponse(
    val statusCode: String?,
    val statusDescription: String?
) : Parcelable

fun AnnulmentResponse.toAnnulment() = if (
    statusCode.isNullOrBlank() ||
    statusDescription.isNullOrBlank()
) {
    null
} else {
    Annulment(statusCode, statusDescription)
}



