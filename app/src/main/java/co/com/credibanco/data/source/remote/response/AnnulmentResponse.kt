package co.com.credibanco.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnnulmentResponse(
    val statusCode: String?,
    val statusDescription: String?
) : Parcelable


