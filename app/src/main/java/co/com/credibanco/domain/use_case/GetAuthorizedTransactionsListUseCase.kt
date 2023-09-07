package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.domain.repository.AuthorizationRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class GetAuthorizedTransactionsListUseCase @Inject constructor(
    private val repository: AuthorizationRepository,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<NoParams, GetAuthorizedTransactionsListUseCaseResult>(coroutineDispatcher) {

    override suspend fun execute(parameters: NoParams) = GetAuthorizedTransactionsListUseCaseResult(
        repository.getAllAuthorizedTransactions()
    )
}

data class GetAuthorizedTransactionsListUseCaseResult(
    val authorizations: List<Authorization>
)
