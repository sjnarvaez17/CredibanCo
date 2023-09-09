package co.com.credibanco.domain.model

data class Authorization(
    val receiptId: String,
    val rrn: String,
    val statusCode: String,
    val statusDescription: String
)
