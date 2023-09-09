package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.domain.repository.AuthorizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAuthorizationWithReceiptIdUseCase @Inject constructor(
    private val repository: AuthorizationRepository,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<GetAuthorizationWithReceiptIdUseCaseParams, GetAuthorizationWithReceiptIdUseCaseResult>(
    coroutineDispatcher
) {

    override suspend fun execute(parameters: GetAuthorizationWithReceiptIdUseCaseParams) =
        GetAuthorizationWithReceiptIdUseCaseResult(
            repository.getAuthorizedTransactionWithReceiptId(parameters.receiptId)
        )
}

data class GetAuthorizationWithReceiptIdUseCaseParams(
    val receiptId: String
)

data class GetAuthorizationWithReceiptIdUseCaseResult(
    val authorizations: List<Authorization>
)
