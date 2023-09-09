package co.com.credibanco.data.repository

import co.com.credibanco.data.source.remote.RemoteDataSource
import co.com.credibanco.data.source.remote.response.toAnnulment
import co.com.credibanco.domain.repository.AnnulmentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnnulmentRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : AnnulmentRepository {

    override suspend fun requestAnnulment(receiptId: String, rrn: String) = remoteDataSource
        .requestAnnulment(
            receiptId,
            rrn
        )
        .toAnnulment()
}
