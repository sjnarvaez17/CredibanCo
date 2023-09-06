package co.com.credibanco.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Annulment(
    val statusCode: String,
    val statusDescription: String
) : Parcelable