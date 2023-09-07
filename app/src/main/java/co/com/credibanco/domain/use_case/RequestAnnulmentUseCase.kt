package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.model.Annulment
import co.com.credibanco.domain.repository.AnnulmentRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class RequestAnnulmentUseCase @Inject constructor(
    private val repository: AnnulmentRepository,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<RequestAnnulmentUseCaseParams, RequestAnnulmentUseCaseResult>(coroutineDispatcher) {

    override suspend fun execute(parameters: RequestAnnulmentUseCaseParams) =
        RequestAnnulmentUseCaseResult(
            repository.requestAnnulment(
                parameters.receiptId,
                parameters.rrn
            )
        )
}

data class RequestAnnulmentUseCaseParams(val receiptId: String, val rrn: String)

data class RequestAnnulmentUseCaseResult(val annulment: Annulment?)
