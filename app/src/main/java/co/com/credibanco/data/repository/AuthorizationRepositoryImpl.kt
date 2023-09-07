package co.com.credibanco.data.repository

import co.com.credibanco.data.source.local.LocalDataSource
import co.com.credibanco.data.source.local.entities.AuthorizationEntity
import co.com.credibanco.data.source.remote.RemoteDataSource
import co.com.credibanco.data.source.remote.response.toAuthorization
import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.domain.repository.AuthorizationRepository
import javax.inject.Inject


//class AuthorizationRepositoryImpl @Inject constructor(
//    private val remoteDataSource: RemoteDataSource,
//    private val localDataSource: LocalDataSource
//) : AuthorizationRepository {

//    companion object {
//        const val APPROVE = "aprobada"
//    }
//
//    override suspend fun fetchAuthorization(
//        id: String,
//        commerceCode: String,
//        terminalCode: String,
//        amount: String,
//        card: String
//    ): Authorization {
//
//        val authorizationResponse =
//            remoteDataSource.fetchAuthorization(id, commerceCode, terminalCode, amount, card)
//        val authorization = authorizationResponse.toAuthorization()
//        if (authorization != null && authorization.statusDescription == APPROVE) {
//            val authorizationEntity = AuthorizationEntity(
//                authorization.receiptId,
//                authorization.rrn,
//                authorization.statusCode,
//                authorization.statusDescription
//            )
//            localDataSource.saveAuthorization(authorizationEntity)
//        }
//    }
//}
