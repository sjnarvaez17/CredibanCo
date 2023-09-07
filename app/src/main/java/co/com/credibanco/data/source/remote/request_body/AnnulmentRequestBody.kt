package co.com.credibanco.data.source.remote.request_body

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnnulmentRequestBody(
    val receiptId: String,
    val rrn: String
) : Parcelable
