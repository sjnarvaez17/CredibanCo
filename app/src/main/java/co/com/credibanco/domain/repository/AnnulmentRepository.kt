package co.com.credibanco.domain.repository

import co.com.credibanco.domain.model.Annulment

interface AnnulmentRepository {

    suspend fun requestAnnulment(
        receiptId: String,
        rrn: String
    ): Annulment?
}
