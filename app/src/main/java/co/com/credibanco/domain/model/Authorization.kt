package co.com.credibanco.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Authorization(
    val receiptId: String,
    val rrn: String,
    val statusCode: String,
    val statusDescription: String
): Parcelable