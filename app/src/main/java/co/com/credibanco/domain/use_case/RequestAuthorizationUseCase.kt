package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.domain.repository.AuthorizationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RequestAuthorizationUseCase @Inject constructor(
    private val repository: AuthorizationRepository,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<RequestAuthorizationUseCaseParams, RequestAuthorizationUseCaseResult>(
    coroutineDispatcher
) {

    override suspend fun execute(parameters: RequestAuthorizationUseCaseParams) =
        RequestAuthorizationUseCaseResult(
            repository.requestAuthorization(
                parameters.id,
                parameters.commerceCode,
                parameters.terminalCode,
                parameters.amount,
                parameters.card
            )
        )
}

data class RequestAuthorizationUseCaseParams(
    val id: String,
    val commerceCode: String,
    val terminalCode: String,
    val amount: String,
    val card: String
)

data class RequestAuthorizationUseCaseResult(
    val authorizations: Authorization?
)
