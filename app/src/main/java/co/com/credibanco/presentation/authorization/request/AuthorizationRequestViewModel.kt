package co.com.credibanco.presentation.authorization.request

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.use_case.GetCurrentCredentialsUseCase
import co.com.credibanco.domain.use_case.NoParams
import co.com.credibanco.domain.use_case.RequestAuthorizationUseCase
import co.com.credibanco.domain.use_case.RequestAuthorizationUseCaseParams
import co.com.credibanco.domain.use_case.Result
import co.com.credibanco.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class AuthorizationRequestViewModel @Inject constructor(
    private val getCurrentCredentialsUseCase: GetCurrentCredentialsUseCase,
    private val requestAuthorizationUseCase: RequestAuthorizationUseCase,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<AuthorizationRequestViewState, AuthorizationRequestViewEvent>(coroutineDispatcher) {

    override fun getInitialState() = AuthorizationRequestViewState.Initial

    override suspend fun processEvent(event: AuthorizationRequestViewEvent) {
        when (event) {
            is AuthorizationRequestViewEvent.Initialize -> processInitializeEvent()
            is AuthorizationRequestViewEvent.AuthorizationRequestClicked -> processAuthorizationRequestClicked(
                event
            )
        }
    }

    private suspend fun processInitializeEvent() {
        when (val result = getCurrentCredentialsUseCase(NoParams)) {
            is Result.Success -> {
                val credentials = result.data.credentials
                setState(
                    AuthorizationRequestViewState.InitializeForm(
                        credentials.commerceCode,
                        credentials.terminalCode
                    )
                )
            }

            is Result.Error -> {
                setState(
                    AuthorizationRequestViewState.Error(
                        result.exception.message.orEmpty()
                    )
                )
            }
        }
    }

    private suspend fun processAuthorizationRequestClicked(
        event: AuthorizationRequestViewEvent.AuthorizationRequestClicked
    ) {
        when (
            val result = requestAuthorizationUseCase(
                RequestAuthorizationUseCaseParams(
                    event.id,
                    event.commerceCode,
                    event.terminalCode,
                    event.amount,
                    event.card
                )
            )
        ) {
            is Result.Success -> {
                val authorization = result.data.authorization

                if (authorization == null) {
                    setState(
                        AuthorizationRequestViewState.Error(
                            "Bad response from server. Please, try again"
                        )
                    )
                    return
                }

                setState(AuthorizationRequestViewState.Content(authorization))
            }

            is Result.Error -> {
                setState(
                    AuthorizationRequestViewState.Error(
                        result.exception.message.orEmpty()
                    )
                )
            }
        }
    }
}
