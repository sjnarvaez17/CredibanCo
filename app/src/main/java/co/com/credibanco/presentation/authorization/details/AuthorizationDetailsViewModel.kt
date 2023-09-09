package co.com.credibanco.presentation.authorization.details

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.use_case.RequestAnnulmentUseCase
import co.com.credibanco.domain.use_case.RequestAnnulmentUseCaseParams
import co.com.credibanco.domain.use_case.Result
import co.com.credibanco.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthorizationDetailsViewModel @Inject constructor(
    private val requestAnnulmentUseCase: RequestAnnulmentUseCase,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : BaseViewModel<AuthorizationDetailsViewState, AuthorizationDetailsViewEvent>(dispatcher) {

    override fun getInitialState() = AuthorizationDetailsViewState.Initial

    override suspend fun processEvent(event: AuthorizationDetailsViewEvent) {
        when (event) {
            AuthorizationDetailsViewEvent.Initialize -> Unit
            is AuthorizationDetailsViewEvent.RequestAnnulment -> processRequestAnnulment(event)
        }
    }

    private suspend fun processRequestAnnulment(event: AuthorizationDetailsViewEvent.RequestAnnulment) {
        setState(AuthorizationDetailsViewState.Loading)

        when (
            val result = requestAnnulmentUseCase(
                RequestAnnulmentUseCaseParams(
                    event.receiptId, event.rrn
                )
            )
        ) {
            is Result.Success -> {
                val annulment = result.data.annulment

                if (annulment == null) {
                    setState(
                        AuthorizationDetailsViewState.Error(
                            "Bad response from server. Please try again"
                        )
                    )
                    return
                }

                setState(AuthorizationDetailsViewState.Content(annulment))
            }

            is Result.Error -> {
                val exception = result.exception

                val messageBuilder = StringBuilder()
                    .append(exception.message.orEmpty())

                if (exception is HttpException) {
                    val response = exception
                        .response()
                        ?.errorBody()
                        ?.string()
                        .orEmpty()

                    messageBuilder.append("\n\n")
                    messageBuilder.append(response)
                }

                setState(
                    AuthorizationDetailsViewState.Error(
                        messageBuilder.toString()
                    )
                )
            }
        }
    }
}
